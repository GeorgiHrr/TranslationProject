package Tokens;

public class KeywordToken extends Token{

    private String value;
    final private String tokenType = "KEYWORD_TOKEN";
    public KeywordToken(String value){
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
