package Generation.Nodes;

import Generation.ASTNode;
import Generation.ASTVisitor;

public class UnaryExpressionNode extends ExpressionNode{
    String operator;
    ASTNode expression;

    public UnaryExpressionNode(String operator, ASTNode expression) {
        this.operator = operator;
        this.expression = expression;
    }
    public UnaryExpressionNode(ASTNode expression ,String operator) {
        this.operator = operator;
        this.expression = expression;
    }

    public ASTNode getExpression() {
        return expression;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        System.out.println("Visiting UnaryExpressionNode with operator: " + operator);
        visitor.visit(this);
    }
}
