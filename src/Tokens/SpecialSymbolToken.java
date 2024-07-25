package Tokens;

public class SpecialSymbolToken extends Token{

    private String value;

    public SpecialSymbolToken(String value){
        super(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
