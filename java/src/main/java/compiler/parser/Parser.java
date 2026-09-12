package compiler.parser;

import compiler.lexer.Scanner;

public class Parser {
    private Scanner scan;
    private char currentToken;

    public Parser(byte[] input) {
        this.scan = new Scanner(input);
        this.currentToken = scan.nextToken();
    }

    private void nextToken() {
        currentToken = scan.nextToken();
    }

    public void parse() {
        expr();
    }

    private void match(char t) {
        if (currentToken == t) {
            nextToken();
        } else {
            throw new Error("syntax error");
        }
    }

    private void expr() {
        digit();
        oper();
    }

    private void digit() {
        if (Character.isDigit(currentToken)) {
            System.out.println("push " + currentToken);
            match(currentToken);
        } else {
            throw new Error("syntax error");
        }
    }

    private void oper() {
        if (currentToken == '+') {
            match('+');
            digit();
            System.out.println("add");
            oper();
        } else if (currentToken == '-') {
            match('-');
            digit();
            System.out.println("sub");
            oper();
        } else if (currentToken == '*') {
            match('*');
            digit();
            System.out.println("mult");
            oper();
        } else if (currentToken == '/') {
            match('/');
            digit();
            System.out.println("div");
            oper();
        }
    }
}