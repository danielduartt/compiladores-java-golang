package compiler.lexer;

public class Scanner {
    private byte[] input;
    private int current;

    public Scanner(byte[] input) {
        this.input = input;
        this.current = 0;
    }

    private char peek() {
        if (current < input.length) return (char) input[current];
        return '\0';
    }

    private void advance() {
        current++;
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(peek())) {
            advance();
        }
    }

    public Token nextToken() {
        skipWhitespace();
        char ch = peek();

        if (ch == '\0') {
            return new Token(TokenType.EOF, "");
        }

        if (Character.isDigit(ch)) {
            StringBuilder number = new StringBuilder();
            while (Character.isDigit(peek())) {
                number.append(peek());
                advance();
            }
            return new Token(TokenType.NUMBER, number.toString());
        }

        advance(); // Consome o operador
        switch (ch) {
            case '+': return new Token(TokenType.PLUS, "+");
            case '-': return new Token(TokenType.MINUS, "-");
            case '*': return new Token(TokenType.MULT, "*");
            case '/': return new Token(TokenType.DIV, "/");
            default:  return new Token(TokenType.ILLEGAL, String.valueOf(ch));
        }
    }
}