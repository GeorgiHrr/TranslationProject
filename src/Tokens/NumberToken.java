package Tokens;

public class NumberToken extends Token{

    private String value;

    public NumberToken(String value){
        super(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
