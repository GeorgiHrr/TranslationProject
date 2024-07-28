package Generation;

import Generation.Nodes.*;

public interface ASTVisitor {
    void visit(ProgramNode node);
    void visit(StatementNode node);
    void visit(BinaryExpressionNode node);
    void visit(UnaryExpressionNode node);
    void visit(IdentNode node);
    void visit(NumberNode node);
    void visit(PrintFuncNode node);
    void visit(ScanfFuncNode node);
}
