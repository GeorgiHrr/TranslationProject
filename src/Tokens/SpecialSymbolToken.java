package Tokens;

public class SpecialSymbolToken extends Token{

    private String value;
    final private String tokenType = "SPECIAL_SYMBOL_TOKEN";
    public SpecialSymbolToken(String value){
        super(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getTokenType() {
        return tokenType;
    }
}
