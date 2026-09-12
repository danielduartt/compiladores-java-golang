package compiler;
import compiler.parser.Parser;

public class Main {
    public static void main(String[] args) {
        String input = """
            let a = 42 + 5 - 8;
            let b = 56 + 8;
            print a + b + 6;        
        """;
        
        System.out.println("Executando o programa:\n" + input);
        System.out.println("--- Saída (Notação Pós-Fixada) ---");
        
        Parser p = new Parser(input.getBytes());
        p.parse();
    }
}