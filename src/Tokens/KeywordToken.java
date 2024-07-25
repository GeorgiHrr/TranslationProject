package Tokens;

public class KeywordToken extends Token{

    private String value;

    public KeywordToken(String value){
        super(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
