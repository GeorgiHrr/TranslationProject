package Generation.Nodes;

import Generation.ASTNode;
import Generation.ASTVisitor;

public class BinaryExpressionNode extends ExpressionNode{
    ASTNode left;
    String operator;
    ASTNode right;

    public BinaryExpressionNode(ASTNode left, String operator, ASTNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public ASTNode getLeft() {
        return left;
    }
    public ASTNode getRight() {
        return right;
    }
    public String getOperator() {
        return operator;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName() {
        return "BinaryExp";
    }

}
