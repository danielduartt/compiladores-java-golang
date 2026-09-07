package lexer

import "fmt"

type TokenType string

const (
	NUMBER  TokenType = "NUMBER"
	PLUS    TokenType = "PLUS"
	MINUS   TokenType = "MINUS"
	MULT    TokenType = "MULT"
	DIV     TokenType = "DIV"
	EOF     TokenType = "EOF"
	ILLEGAL TokenType = "ILLEGAL"
)

type Token struct {
	Type   TokenType
	Lexeme string
}

func (t Token) String() string {
	return fmt.Sprintf("Token{type=%s, lexeme='%s'}", t.Type, t.Lexeme)
}
