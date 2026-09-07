package lexer

import "unicode"

type Scanner struct {
	input   []byte
	current int
}

func NewScanner(input []byte) *Scanner {
	return &Scanner{input: input, current: 0}
}

func (s *Scanner) peek() rune {
	if s.current < len(s.input) {
		return rune(s.input[s.current])
	}
	return '\x00'
}

func (s *Scanner) advance() {
	s.current++
}

func (s *Scanner) skipWhitespace() {
	for unicode.IsSpace(s.peek()) {
		s.advance()
	}
}

func (s *Scanner) NextToken() Token {
	s.skipWhitespace()
	ch := s.peek()

	if ch == '\x00' {
		return Token{Type: EOF, Lexeme: ""}
	}

	if unicode.IsDigit(ch) {
		lexeme := ""
		for unicode.IsDigit(s.peek()) {
			lexeme += string(s.peek())
			s.advance()
		}
		return Token{Type: NUMBER, Lexeme: lexeme}
	}

	s.advance() // Consome o operador
	switch ch {
	case '+':
		return Token{Type: PLUS, Lexeme: "+"}
	case '-':
		return Token{Type: MINUS, Lexeme: "-"}
	case '*':
		return Token{Type: MULT, Lexeme: "*"}
	case '/':
		return Token{Type: DIV, Lexeme: "/"}
	default:
		return Token{Type: ILLEGAL, Lexeme: string(ch)}
	}
}
