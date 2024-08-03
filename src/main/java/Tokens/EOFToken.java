package Tokens;

public class EOFToken extends Token{
    public EOFToken(String value) {
        super(value);
    }

    public String getTokenType() {
        return "EOF_TOKEN";
    }
}
