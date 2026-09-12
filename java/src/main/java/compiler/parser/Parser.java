package compiler.parser;

import compiler.lexer.Scanner;
import compiler.lexer.Token;
import compiler.lexer.TokenType;

public class Parser {
    private Scanner scan;
    private Token currentToken;
    private StringBuilder out; // Acumulador da tradução

    public Parser(byte[] input) {
        this.scan = new Scanner(input);
        this.currentToken = scan.nextToken();
        this.out = new StringBuilder();
    }

    // Novo método para capturar a string completa
    public String output() {
        return out.toString();
    }

    // Centraliza a emissão de comandos
    private void emit(String command) {
        out.append(command).append(System.lineSeparator());
    }

    private void nextToken() {
        this.currentToken = scan.nextToken();
    }

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

    private void statements() {
        while (currentToken.type != TokenType.EOF) {
            statement();
        }
    }

    private void statement() {
        if (currentToken.type == TokenType.PRINT) {
            printStatement();
        } else if (currentToken.type == TokenType.LET) {
            letStatement();
        } else {
            throw new Error("syntax error");
        }
    }

    private void printStatement() {
        match(TokenType.PRINT);
        expr();
        emit("print");
        match(TokenType.SEMICOLON);
    }

    private void letStatement() {
        match(TokenType.LET);
        String id = currentToken.lexeme; 
        match(TokenType.IDENT);
        match(TokenType.EQ);
        expr();
        emit("pop " + id);
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
            emit("push " + currentToken.lexeme);
            match(TokenType.IDENT);
        } else {
            throw new Error("syntax error");
        }
    }

    private void number() {
        emit("push " + currentToken.lexeme);
        match(TokenType.NUMBER);
    }

    private void oper() {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            term(); 
            emit("add");
            oper();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            term();
            emit("sub");
            oper();
        } else if (currentToken.type == TokenType.MULT) {
            match(TokenType.MULT);
            term();
            emit("mult");
            oper();
        } else if (currentToken.type == TokenType.DIV) {
            match(TokenType.DIV);
            term();
            emit("div");
            oper();
        }
    }
}