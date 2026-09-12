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

    // O ponto de entrada agora é o comando 'let'
    public void parse() {
        letStatement();
    }

    private void match(TokenType t) {
        if (currentToken.type == t) {
            nextToken();
        } else {
            throw new Error("syntax error");
        }
    }

    // Regra: letStatement -> 'let' identifier '=' expr ';'
    private void letStatement() {
        match(TokenType.LET);
        
        // Salva o nome da variável antes de consumir o token
        String id = currentToken.lexeme; 
        match(TokenType.IDENT);
        
        match(TokenType.EQ);
        expr();
        
        // Ação semântica de atribuição (pop)
        System.out.println("pop " + id);
        match(TokenType.SEMICOLON);
    }

    // Regra: expr -> term oper
    private void expr() {
        term();
        oper();
    }

    // Regra: term -> number | identifier
    private void term() {
        if (currentToken.type == TokenType.NUMBER) {
            number();
        } else if (currentToken.type == TokenType.IDENT) {
            System.out.println("push " + currentToken.lexeme);
            match(TokenType.IDENT);
        } else {
            throw new Error("syntax error");
        }
    }

    private void number() {
        System.out.println("push " + currentToken.lexeme);
        match(TokenType.NUMBER);
    }

    // Regra: oper -> + term oper | - term oper | * term oper | / term oper | ε
    private void oper() {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            term(); // Atualizado de number() para term()
            System.out.println("add");
            oper();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            term();
            System.out.println("sub");
            oper();
        } else if (currentToken.type == TokenType.MULT) {
            match(TokenType.MULT);
            term();
            System.out.println("mult");
            oper();
        } else if (currentToken.type == TokenType.DIV) {
            match(TokenType.DIV);
            term();
            System.out.println("div");
            oper();
        }
    }
}