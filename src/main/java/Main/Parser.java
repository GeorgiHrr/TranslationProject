package Main;

import Generation.ASTNode;
import Generation.ASTVisitor;
import Generation.ByteCodeGenerator;
import Generation.Nodes.*;
import TableSymbols.Symbol;
import Tokens.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<Token> tokens = new ArrayList<Token>();
    Table symbolTable;
    private int currentToken = 0;
    public Parser(Tokenization tokenization, Table symbolTable){
        this.symbolTable = symbolTable;
        this.tokens = tokenization.getTokens();
        ProgramNode program = parse();
        ASTVisitor visitor = new ByteCodeGenerator(this.symbolTable);
        program.accept(visitor);
        symbolTable.exitScope();
    }
    public void nextToken(){
        currentToken++;
    }

    public boolean checkIdent(){
        if (tokens.get(currentToken) instanceof IdentToken){
            nextToken();
            return true;
        }
        return false;
    }
    public boolean checkKeyword(String keyword){
        if (tokens.get(currentToken) instanceof KeywordToken && tokens.get(currentToken).getValue().equals(keyword)){
            nextToken();
            return true;
        }
        return false;
    }
    public boolean checkNumber(){
        if (tokens.get(currentToken) instanceof NumberToken){
            nextToken();
            return true;
        }
        return false;
    }
    public boolean checkSpecialSymbol(String specialSymbol){
        if (tokens.get(currentToken) instanceof SpecialSymbolToken && tokens.get(currentToken).getValue().equals(specialSymbol)){
            nextToken();
            return true;
        }
        return false;
    }
    public ProgramNode parse(){
        return isProgram();
    }

    //[1] Program = {Statement}.
    public ProgramNode isProgram(){
        symbolTable.enterScope();
        List<StatementNode> statements = new ArrayList<>();
        while(!tokens.get(currentToken).getClass().equals(EOFToken.class)){
            StatementNode stmn = isStatement();
            if (stmn != null){
                statements.add(stmn);
            }
            if (tokens.get(currentToken).getClass().equals(EOFToken.class)){
                return new ProgramNode(statements);
            }
        }
        return new ProgramNode(statements);
    }
    //[2] Statement = [Expression] ';'.
    public StatementNode isStatement(){
        ASTNode expr = isExpression();
        if(expr != null){
            if(checkSpecialSymbol(";")){
                return new StatementNode(expr);
            } else{
                System.out.println("Error: Missing semicolon");
            }
        }
        if(checkSpecialSymbol(";")){
            return new StatementNode(null);
        }
        return null;
    }
    //[3] Expression = BitwiseAndExpression {'|' BitwiseAndExpression}.
    public ExpressionNode isExpression(){
        ExpressionNode expr = isBitwiseAndExpression();
        while(checkSpecialSymbol("|")){
            ExpressionNode right = isBitwiseAndExpression();
            expr = new BinaryExpressionNode(expr,"|",right);
        }
        return expr;
    }
    //[4] BitwiseAndExpression = AdditiveExpression {'&' AdditiveExpression}.
    public ExpressionNode isBitwiseAndExpression(){
        ExpressionNode expr = isAdditiveExpression();
        while(checkSpecialSymbol("&")){
            ExpressionNode right = isAdditiveExpression();
            expr = new BinaryExpressionNode(expr,"&",right);
        }
        return expr;
    }
    //[5] AdditiveExpression = MultiplicativeExpression {('+' | '-') MultiplicativeExpression}.
    public ExpressionNode isAdditiveExpression(){
        ExpressionNode expr = isMultiplicativeExpression();
        while(checkSpecialSymbol("+") || checkSpecialSymbol("-")){
            String operator = tokens.get(currentToken - 1).getValue();
            ExpressionNode right = isMultiplicativeExpression();
            expr = new BinaryExpressionNode(expr,operator,right);
        }
        return expr;
    }
    //[6] MultiplicativeExpression = PrimaryExpression {('*' | '/' | '%') PrimaryExpression}.
    public ExpressionNode isMultiplicativeExpression(){
        ExpressionNode expr = isPrimaryExpression();
        while(checkSpecialSymbol("*") || checkSpecialSymbol("/") || checkSpecialSymbol("%")){
            String operator = tokens.get(currentToken - 1).getValue();
            ExpressionNode right = isPrimaryExpression();
            expr = new BinaryExpressionNode(expr, operator, right);
        }
        return expr;
    }
    //[7] PrimaryExpression = Ident ['=' Expression] | '~' PrimaryExpression | '++' Ident | '--' Ident | Ident '++' | Ident '--' |
    //                        Number | PrintFunc | ScanfFunc | '(' Expression ')'.
    public ExpressionNode isPrimaryExpression(){
        if(checkIdent()){
            String identName = tokens.get(currentToken - 1).getValue();
            if (checkSpecialSymbol("=")){
                symbolTable.addSymbol(identName, "int",null);
                ExpressionNode expr = isExpression();
                return new BinaryExpressionNode(new IdentNode(identName), "=", expr);
            }
            if ((checkSpecialSymbol("++") || checkSpecialSymbol("--"))){
                Symbol symbol = symbolTable.getSymbol(identName);
                if (symbol == null) {
                    System.out.println("Error: Undefined identifier " + identName);
                }
                return new UnaryExpressionNode(new IdentNode(identName), tokens.get(currentToken - 1).getValue());
            }
            return new IdentNode(identName);
        }
        if (checkSpecialSymbol("~")){
            ExpressionNode expr = isPrimaryExpression();
            return new UnaryExpressionNode("~", expr);
        }
        if (checkSpecialSymbol("++") || checkSpecialSymbol("--")){
            String identName = tokens.get(currentToken - 1).getValue();
            Symbol symbol = symbolTable.getSymbol(identName);
            if (symbol == null) {
                System.out.println("Error: Undefined identifier " + identName);
            }
            checkIdent();
            return new UnaryExpressionNode(tokens.get(currentToken - 1).getValue(), new IdentNode(identName));
        }

        if (checkNumber()){
            return new NumberNode(tokens.get(currentToken - 1).getValue());
        }
        if (checkKeyword("printf")){
            if(checkSpecialSymbol("(")){
                ExpressionNode expr = isExpression();
                if (checkSpecialSymbol(")")){
                    return new PrintFuncNode(expr);
                }
            }
        }
        if (isScanfFunc()){
            return new ScanfFuncNode();
        }
        if(checkSpecialSymbol("(")){
            ExpressionNode expr = isExpression();
            if (checkSpecialSymbol(")")){
                return expr;
            }
        }
        return null;
    }
    //[8] PrintFunc = 'printf' '(' Expression ')'.
    public boolean isPrintFunc(){
        if(checkKeyword("printf")){
            if(checkSpecialSymbol("(")){
                isExpression();
                return checkSpecialSymbol(")");
            }
        }
        return false;
    }
    //[9] ScanfFunc = 'scanf' '(' ')'.
    public boolean isScanfFunc(){
        if (checkKeyword("scanf")){
            return checkSpecialSymbol("(") && checkSpecialSymbol(")");
        }
        return false;
    }
}