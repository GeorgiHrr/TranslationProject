package Generation.Nodes;

import Generation.ASTNode;
import Generation.ASTVisitor;

import java.util.List;

public class ProgramNode extends ASTNode {
    List<StatementNode> statements;
    public ProgramNode(List<StatementNode> statements) {
        this.statements = statements;
    }

    public List<StatementNode> getStatements() {
        return statements;
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
    @Override
    public String getName() {
        return "Program";
    }
}
