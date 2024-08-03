package Tokens;

public class NumberToken extends Token{

    public NumberToken(String value){
        super(value);
    }
    public String getTokenType() {
        return "NUMBER_TOKEN";
    }
}
