package Tokens;

public class KeywordToken extends Token{
    public KeywordToken(String value){
        super(value);
    }
    public String getTokenType() {
        return "KEYWORD_TOKEN";
    }


}
