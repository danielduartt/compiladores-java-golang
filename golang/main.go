package main

import (
	"fmt"
	"my_compiler/parser"
)

func main() {
	input := "8+5-7*9"
	fmt.Println("Expressão de entrada:", input)
	fmt.Println("-------------------------")

	p := parser.NewParser([]byte(input))
	p.Parse()
}
