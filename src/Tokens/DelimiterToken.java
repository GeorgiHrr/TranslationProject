package Tokens;

public class DelimiterToken extends Token{

    private String value;
    final private String tokenType = "DELIMITER_TOKEN";

    public DelimiterToken(String value){
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
