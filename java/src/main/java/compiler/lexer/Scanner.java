package compiler.lexer;

public class Scanner {
    private byte[] input;
    private int current;

    public Scanner(byte[] input) {
        this.input = input;
        this.current = 0;
    }

    private char peek() {
        if (current < input.length) {
            return (char) input[current];
        }
        return '\0';
    }

    private void advance() {
        char ch = peek();
        if (ch != '\0') {
            current++;
        }
    }

    // Retorna apenas char por enquanto, para fins de refatoração
    public char nextToken() {
        char ch = peek();

        if (Character.isDigit(ch)) {
            advance();
            return ch;
        }

        switch (ch) {
            case '+':
            case '-':
            case '*': // Atividade extra mantida
            case '/': // Atividade extra mantida
                advance();
                return ch;
            default:
                break;
        }

        return '\0';
    }
}