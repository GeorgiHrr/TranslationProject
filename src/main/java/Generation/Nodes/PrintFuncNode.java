package Generation.Nodes;

import Generation.ASTNode;
import Generation.ASTVisitor;

public class PrintFuncNode extends ExpressionNode{
    ASTNode expression;

    public PrintFuncNode(ASTNode expression) {
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
        return "PrintFunc";
    }
}
