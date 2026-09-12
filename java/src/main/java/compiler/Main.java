package compiler;
import compiler.parser.Parser;

public class Main {
    public static void main(String[] args) {
        String input = "let a = 42 + 5 - 8;";
        System.out.println("Executando o comando: " + input);
        System.out.println("-------------------------");
        
        Parser p = new Parser(input.getBytes());
        p.parse();
    }
}