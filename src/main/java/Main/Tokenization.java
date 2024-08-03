package Main;

import Tokens.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tokenization {
    Scanner scanner = new Scanner(System.in);
    StringBuilder str = new StringBuilder();
    private List<String> lines;
    private List<Token> tokens = new ArrayList<Token>();

    final private String keywords=" scanf printf ";
    final private String specialSymbols="=();+-*|/%&~";
    final private String doubleSpecialSymbols=" ++ -- ";


    private int row = 0;
    private int col = 0;
    private char ch;

    private boolean isReading = true;

    public Tokenization(List<String> lines){
        this.lines = lines;
        ch = this.lines.get(row).charAt(col);
        createToken();
        addEOFToken();

//        for(Token token : tokens){
//            System.out.print(token.getTokenType()+"("+token.getValue()+")"+", ");
//        }
//        System.out.println();

    }

    public List<Token> getTokens() {
        return tokens;
    }

    private void readNextChar(){
        col++;
        goToNextLine();
        if(row >= lines.size()){
            isReading = false;
            ch = ' ';
            return;
        }
        ch = lines.get(row).charAt(col);
    }
    private void addEOFToken(){
        tokens.add(new EOFToken("FIN"));
    }

    private void goToNextLine(){
        if(col == lines.get(row).length()){
            row++;
            col = 0;
        }
    }

    private void createToken(){
        while(isReading){
            //Checking if it's and Ident or a Keyword Token
            if (ch>='a' && ch<='z' || ch>='A' && ch<='Z' || ch=='_') {
                while(ch>='a' && ch<='z' || ch>='A' && ch<='Z' || ch=='_' || ch>='0' && ch<='9'){
                    buildString(ch);
                    readNextChar();
                }
                if(keywords.contains(" "+str.toString()+" ")){
                    tokens.add(new KeywordToken(str.toString()));
                    resetBuilder();
                }else{
                    tokens.add(new IdentToken(str.toString()));
                    resetBuilder();
                }
            }
            //Checking if it's a Number Token
            if(ch>='0' && ch<='9'){
                while (ch>='0' && ch<='9') {
                    buildString(ch);
                    readNextChar();
                }
                tokens.add(new NumberToken(str.toString()));
                resetBuilder();
            }
            //Checking if it's a Delimiter Token
            if(ch==' '){
                readNextChar();
                resetBuilder();
            }
            //Checking if it's a SpecialSymbol Token
            if(specialSymbols.contains(Character.toString(ch))){
                char ch1 = ch;
                readNextChar();
                char ch2 = ch;
                buildString(ch1);
                buildString(ch2);
                if(doubleSpecialSymbols.contains(" " + str + " ")){
                    tokens.add(new SpecialSymbolToken(str.toString()));
                    resetBuilder();
                    readNextChar();
                }else{
                    tokens.add(new SpecialSymbolToken(Character.toString(ch1)));
                    resetBuilder();
                }
            }
        }
    }

    private void buildString(char character){
        str.append(character);
    }

    private void resetBuilder(){
        str.setLength(0);
    }
}