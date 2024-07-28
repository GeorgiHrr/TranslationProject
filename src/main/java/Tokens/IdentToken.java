package Tokens;

public class IdentToken extends Token{

    private String value;
    final private String tokenType = "IDENT_TOKEN";
    public IdentToken(String value){
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
