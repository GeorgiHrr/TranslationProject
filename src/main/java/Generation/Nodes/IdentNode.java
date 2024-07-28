package Generation.Nodes;

import Generation.ASTVisitor;

public class IdentNode extends ExpressionNode{
    String name;

    public IdentNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        System.out.println("Visiting IdentNode with name: " + name);
        visitor.visit(this);
    }
}
