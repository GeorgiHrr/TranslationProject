package Generation;

public abstract class ASTNode {
    public abstract void accept(ASTVisitor visitor);
}
