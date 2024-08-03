package Tokens;

public class DelimiterToken extends Token{

    public DelimiterToken(String value){
        super(value);
    }
    public String getTokenType() {
        return "DELIMITER_TOKEN";
    }
}
