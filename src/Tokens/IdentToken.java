package Tokens;

public class IdentToken extends Token{

    private String value;

    public IdentToken(String value){
        super(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
