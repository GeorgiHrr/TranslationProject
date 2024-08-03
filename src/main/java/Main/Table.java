package Main;

import TableSymbols.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Table {

    private Stack<HashMap<String, Symbol>> symbolTable;
    public Table() {
        this.symbolTable = new Stack<HashMap<String, Symbol>>();
        enterScope();
        addPredefinedSymbols();
    }
    private void addPredefinedSymbols(){
        addSymbol("int", "type", null);
        addSymbol("printf", "func", null);
        addSymbol("scanf", "func", null);
    }
    // Enters a scope by pushing a HashMap where symbols are stored;
    public void enterScope(){
        symbolTable.push(new HashMap<String, Symbol>());
    }
    // Pops the top scope off the stack.
    public void exitScope(){
        if (!symbolTable.isEmpty() && symbolTable.size() > 1){
            symbolTable.pop();
        }
    }
    // Adds a symbol to the current scope.
    public void addSymbol(String name, String type, String value){
        if (!symbolTable.isEmpty()) {
            symbolTable.peek().put(name, new Symbol(name, type, value));
        }
    }
    // Looks up a symbol, starting from the current scope and going up the stack.
    public Symbol getSymbol(String name){
        for (int i = symbolTable.size() - 1; i >= 0; i--) {
            HashMap<String, Symbol> scope = symbolTable.get(i);
            if (scope.containsKey(name)) {
                return scope.get(name);
            }
        }
        return null; // Symbol not found in any scope
    }
    //  Prints all symbols in all scopes.
    public void printTable() {
        for (int i = 0; i < symbolTable.size(); i++) {
            System.out.println("Scope Level " + i + ":");
            for (HashMap.Entry<String, Symbol> entry : symbolTable.get(i).entrySet()) {
                System.out.println(entry.getValue().toString());
            }
        }
    }
    public HashMap<String, Symbol> getElement(int Index){
        return symbolTable.get(Index);
    }

    public List<String> getAllSymbolNames() {
        ArrayList<String> symbolNames = new ArrayList<>();
        for (int i = 1; i < symbolTable.size(); i++) {
            HashMap<String, Symbol> scope = symbolTable.get(i);
            symbolNames.addAll(scope.keySet());
        }
        return symbolNames;
    }
}
