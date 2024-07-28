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
        System.out.println("Visiting StatementNode");
        visitor.visit(this);
    }
}
