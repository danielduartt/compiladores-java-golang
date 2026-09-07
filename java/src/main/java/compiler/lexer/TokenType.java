package compiler.lexer;

public enum TokenType {
    NUMBER,   // [0-9]+
    PLUS,     // +
    MINUS,    // -
    MULT,     // *
    DIV,      // /
    EOF,      // Fim do arquivo
    ILLEGAL   // Caractere não reconhecido
}