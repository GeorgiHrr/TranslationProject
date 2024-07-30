package Generation;

public abstract class ASTNode {
    public String name;
    public abstract void accept(ASTVisitor visitor);
    public abstract String getName();
}
