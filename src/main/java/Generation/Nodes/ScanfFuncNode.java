package Generation.Nodes;

import Generation.ASTVisitor;

public class ScanfFuncNode extends ExpressionNode{

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName() {
        return "ScanFunc";
    }
}
