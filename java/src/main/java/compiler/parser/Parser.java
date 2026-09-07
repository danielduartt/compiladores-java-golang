package compiler.parser; 

public class Parser {
    private byte[] input; 
    private int current; // atual 

    //Construtor
    public Parser(byte[] input){
        this.input = input; 
        this.current = 0; //Iniciando a leitura do primeiro caractere
    }

    public void parse () {
        expr();
    }

    // Método para retornar o caractere atual 
    public char peek() {
        if (current < input.length){
            return (char)input[current];
        }
        return '\0';
    }

    // Método para verificar e consumir o caractere
    public void match(char c){
        if (c == peek()){
            current++; 
        }else{
            throw new Error("Erro Sintatico");
        }
    }

    // Regra: expr -> digit oper
    private void expr() {
        digit();
        oper();
    }

    // Regra: digit -> 0 | 1 | ... | 9
    private void digit() {
        if (Character.isDigit(peek())) {
            System.out.println("push " + peek());
            match(peek());
        } else {
            throw new Error("syntax error");
        }
    }

    // Regra: oper -> + digit oper | - digit oper | * digit oper | / digit oper | ε
    private void oper() {
        if (peek() == '+') {
            match('+');
            digit();
            System.out.println("add");
            oper();
        } else if (peek() == '-') {
            match('-');
            digit();
            System.out.println("sub");
            oper();
        }
        else if (peek() == '*') {
            match('*');
            digit();
            System.out.println("mult");
            oper();
        } else if (peek() == '/') {
            match('/');
            digit();
            System.out.println("div");
            oper();
        }
    }
}