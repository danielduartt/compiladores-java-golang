package compiler;

import compiler.parser.Parser;
import compiler.interpreter.Interpretador;

public class Main {
    public static void main(String[] args) {
        String input = """
            let a = 42 + 2;
            let b = 15 + 3;
            print a + b;        
        """;
        
        System.out.println("--- Compilando e Executando ---");
        
        // Front-end: Análise e Tradução
        Parser p = new Parser(input.getBytes());
        p.parse();

        // Back-end: Execução na Máquina Virtual
        Interpretador i = new Interpretador(p.output());
        i.run();
    }
}