package Tokens;

public class EOFToken extends Token{
    final private String tokenType = "EOF_TOKEN";
    public EOFToken(String value) {
        super(value);
    }

    public String getTokenType() {
        return tokenType;
    }
}
