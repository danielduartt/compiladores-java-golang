package compiler.parser;

import compiler.lexer.Scanner;
import compiler.lexer.Token;
import compiler.lexer.TokenType;

public class Parser {
    private Scanner scan;
    private Token currentToken;

    public Parser(byte[] input) {
        this.scan = new Scanner(input);
        this.currentToken = scan.nextToken();
    }

    private void nextToken() {
        this.currentToken = scan.nextToken();
    }

    public void parse() {
        expr();
    }

    // Agora o match verifica o tipo de token, não o lexema
    private void match(TokenType t) {
        if (currentToken.type == t) {
            nextToken();
        } else {
            throw new Error("syntax error");
        }
    }

    // Regra: expr -> number oper
    private void expr() {
        number();
        oper();
    }

    // Método que substitui o antigo digit()
    private void number() {
        System.out.println("push " + currentToken.lexeme);
        match(TokenType.NUMBER);
    }

    // Regra oper validando pelos tipos de token
    private void oper() {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            number();
            System.out.println("add");
            oper();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            number();
            System.out.println("sub");
            oper();
        } else if (currentToken.type == TokenType.MULT) {
            match(TokenType.MULT);
            number();
            System.out.println("mult");
            oper();
        } else if (currentToken.type == TokenType.DIV) {
            match(TokenType.DIV);
            number();
            System.out.println("div");
            oper();
        }
    }
}