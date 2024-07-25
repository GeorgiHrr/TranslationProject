import Tokens.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<Token> tokens = new ArrayList<Token>();
    private int currentToken = 0;
    public Parser(Tokenization tokenization) {
        tokens = tokenization.getTokens();
        System.out.println();
        System.out.println();
        System.out.println(parse());
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
    public boolean parse(){
        return isProgram();
    }

    //[1] Program = {Statement}.
    public boolean isProgram(){
        while(!tokens.get(currentToken).getClass().equals(EOFToken.class)){
            isStatement();
            if (tokens.get(currentToken).getClass().equals(EOFToken.class)){
                return true;
            }
        }
        return false;
    }
    //[2] Statement = [Expression] ';'.
    public boolean isStatement(){
        if(isExpression()){
            return checkSpecialSymbol(";");
        }
        if(checkSpecialSymbol(";")){
            return true;
        }
        return false;
    }
    //[3] Expression = BitwiseAndExpression {'|' BitwiseAndExpression}.
    public boolean isExpression(){
        if(isBitwiseAndExpression()){
            while(checkSpecialSymbol("|")){
                isBitwiseAndExpression();
            }
            return true;
        }
        return false;
    }
    //[4] BitwiseAndExpression = AdditiveExpression {'&' AdditiveExpression}.
    public boolean isBitwiseAndExpression(){
        if(isAdditiveExpression()){
            while(checkSpecialSymbol("&")){
                isAdditiveExpression();
            }
            return true;
        }
        return false;
    }
    //[5] AdditiveExpression = MultiplicativeExpression {('+' | '-') MultiplicativeExpression}.
    public boolean isAdditiveExpression(){
        if (isMultiplicativeExpression()){
            while(checkSpecialSymbol("+") || checkSpecialSymbol("-")){
                isMultiplicativeExpression();
            }
            return true;
        }
        return false;
    }
    //[6] MultiplicativeExpression = PrimaryExpression {('*' | '/' | '%') PrimaryExpression}.
    public boolean isMultiplicativeExpression(){
        if (isPrimaryExpression()){
            while(checkSpecialSymbol("*") || checkSpecialSymbol("/") || checkSpecialSymbol("%")){
                isPrimaryExpression();
            }
            return true;
        }
        return false;
    }
    //[7] PrimaryExpression = Ident ['=' Expression] | '~' PrimaryExpression | '++' Ident | '--' Ident | Ident '++' | Ident '--' |
    //                        Number | PrintFunc | ScanfFunc | '(' Expression ')'.
    public boolean isPrimaryExpression(){
        if(checkIdent()){
            if (checkSpecialSymbol("=")){
                return isExpression();
            }
            return true;
        }
        if (checkSpecialSymbol("~")){
            return isPrimaryExpression();
        }
        if (checkSpecialSymbol("++")){
            return checkIdent();
        }
        if (checkSpecialSymbol("--")){
            return checkIdent();
        }
        if (checkIdent()){
            return checkSpecialSymbol("++");
        }
        if(checkIdent()){
            return checkSpecialSymbol("--");
        }
        if (checkNumber()){
            return true;
        }
        if (isPrintFunc()){
            return true;
        }
        if (isScanfFunc()){
            return true;
        }
        if(checkSpecialSymbol("(")){
            if (isExpression()){
                return checkSpecialSymbol(")");
            }
        }
        return false;
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
