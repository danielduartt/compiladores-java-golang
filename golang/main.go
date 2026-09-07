package main

import (
	"fmt"
	"my_compiler/lexer"
)

func main() {
	input := "45 + 89 - 876 * 2"
	fmt.Println("Analisando lexicamente:", input)

	scanner := lexer.NewScanner([]byte(input))

	for {
		token := scanner.NextToken()
		fmt.Println(token)
		if token.Type == lexer.EOF {
			break
		}
	}
}
