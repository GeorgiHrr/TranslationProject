package Tokens;

public class NumberToken extends Token{

    private String value;
    final private String tokenType = "NUMBER_TOKEN";
    public NumberToken(String value){
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
