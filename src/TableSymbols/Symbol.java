package TableSymbols;

public class Symbol {
    private String name;
    private String type; // This can be 'int', 'func', 'keyword', etc.
    private String value; // The value of the identifier, if any (for variables)

    public Symbol(String name, String type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
