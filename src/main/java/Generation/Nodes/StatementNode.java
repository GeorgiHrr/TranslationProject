package Generation.Nodes;


import Generation.ASTNode;
import Generation.ASTVisitor;

public class StatementNode extends ASTNode {
    ASTNode expression;

    public StatementNode(ASTNode expression) {
        this.expression = expression;
    }

    public ASTNode getExpression() {
        return expression;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName() {
        return "Statement";
    }
}
