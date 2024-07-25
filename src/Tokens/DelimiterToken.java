package Tokens;

public class DelimiterToken extends Token{

    private String value;

    public DelimiterToken(String value){
        super(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
