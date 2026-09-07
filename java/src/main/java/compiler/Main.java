package compiler;
import compiler.lexer.Scanner;
import compiler.lexer.Token;
import compiler.lexer.TokenType;

public class Main {
    public static void main(String[] args) {
        String input = "45 + 89 - 876 * 2"; // Agora com espaços e números grandes!
        System.out.println("Analisando lexicamente: " + input);
        
        Scanner scanner = new Scanner(input.getBytes());
        Token token;
        
        do {
            token = scanner.nextToken();
            System.out.println(token);
        } while (token.type != TokenType.EOF);
    }
}