package Generation.Nodes;

import Generation.ASTVisitor;

public class NumberNode extends ExpressionNode{
    String value;

    public NumberNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        System.out.println("Visiting NumberNode with value: " + value);
        visitor.visit(this);
    }
}
