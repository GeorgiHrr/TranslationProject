package Tokens;

public class IdentToken extends Token{

    public IdentToken(String value){
        super(value);
    }

    public String getTokenType() {
        return "IDENT_TOKEN";
    }
}
