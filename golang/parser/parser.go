package parser

import (
	"fmt"
	"unicode"
)

type Parser struct {
	input   []byte
	current int
}

// Semelhante a um construtor no Java
func NewParser(input []byte) *Parser {
	return &Parser{
		input:   input,
		current: 0,
	}
}

func (p *Parser) Parse() {
	p.expr()
}

func (p *Parser) peek() rune {
	if p.current < len(p.input) {
		return rune(p.input[p.current])
	}
	return '\x00'
}

func (p *Parser) match(c rune) {
	if c == p.peek() {
		p.current++
	} else {
		panic(fmt.Sprintf("syntax error: esperava %c", c))
	}
}

func (p *Parser) expr() {
	p.digit()
	p.oper()
}

func (p *Parser) digit() {
	if unicode.IsDigit(p.peek()) {
		fmt.Printf("push %c\n", p.peek())
		p.match(p.peek())
	} else {
		panic("syntax error: esperava um dígito")
	}
}

func (p *Parser) oper() {
	switch p.peek() {
	case '+':
		p.match('+')
		p.digit()
		fmt.Println("add")
		p.oper()
	case '-':
		p.match('-')
		p.digit()
		fmt.Println("sub")
		p.oper()
	case '*':
		p.match('*')
		p.digit()
		fmt.Println("mult")
		p.oper()
	case '/':
		p.match('/')
		p.digit()
		fmt.Println("div")
		p.oper()
	}
}
