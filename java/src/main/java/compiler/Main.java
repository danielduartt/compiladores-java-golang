package compiler;

import compiler.parser.Parser;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = "8+5-7*9";
        
        System.out.println("Expressão de entrada: " + input);
        System.out.println("-------------------------");
        
        Parser p = new Parser(input.getBytes());
        p.parse();
    }
}