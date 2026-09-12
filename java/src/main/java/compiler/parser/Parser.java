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

    // Ponto de entrada atualizado para suportar múltiplos comandos
    public void parse() {
        statements();
    }

    private void match(TokenType t) {
        if (currentToken.type == t) {
            nextToken();
        } else {
            throw new Error("syntax error");
        }
    }

    // Regra: statements -> statement*
    private void statements() {
        while (currentToken.type != TokenType.EOF) {
            statement();
        }
    }

    // Regra: statement -> printStatement | letStatement
    private void statement() {
        if (currentToken.type == TokenType.PRINT) {
            printStatement();
        } else if (currentToken.type == TokenType.LET) {
            letStatement();
        } else {
            throw new Error("syntax error");
        }
    }

    // Regra: printStatement -> 'print' expr ';'
    private void printStatement() {
        match(TokenType.PRINT);
        expr();
        System.out.println("print");
        match(TokenType.SEMICOLON);
    }

    // Regra: letStatement -> 'let' identifier '=' expr ';'
    private void letStatement() {
        match(TokenType.LET);
        String id = currentToken.lexeme; 
        match(TokenType.IDENT);
        match(TokenType.EQ);
        expr();
        System.out.println("pop " + id);
        match(TokenType.SEMICOLON);
    }

    private void expr() {
        term();
        oper();
    }

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

    private void oper() {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            term(); 
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