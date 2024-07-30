package Generation.Nodes;

import Generation.ASTVisitor;

public class IdentNode extends ExpressionNode{
    String name;

    public IdentNode(String name) {
        this.name = name;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
