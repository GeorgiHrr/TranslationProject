package Generation.Nodes;

import Generation.ASTVisitor;

public class ScanfFuncNode extends ExpressionNode{

    @Override
    public void accept(ASTVisitor visitor) {
        System.out.println("Visiting ScanfFuncNode");
        visitor.visit(this);
    }
}
