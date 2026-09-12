package compiler;
import compiler.parser.Parser;

public class Main {
    public static void main(String[] args) {
        // Expressão contendo espaços para testar o skipWhitespace
        String input = "45  + 89   -       876 * 2";
        System.out.println("Analisando e traduzindo: " + input);
        System.out.println("-------------------------");
        
        Parser p = new Parser(input.getBytes());
        p.parse();
    }
}