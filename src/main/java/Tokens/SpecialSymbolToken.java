package Tokens;

public class SpecialSymbolToken extends Token{
    public SpecialSymbolToken(String value){
        super(value);
    }

    public String getTokenType() {
        return "SPECIAL_SYMBOL_TOKEN";
    }
}
