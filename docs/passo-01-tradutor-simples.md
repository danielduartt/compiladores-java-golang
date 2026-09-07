# 📖 Estudos - Passo 1: Um Simples Tradutor

**Branch:** `passo-01-tradutor-simples`  
**Material de Referência:** Estudo de Compiladores com Java e Golang 

## 1. Objetivo da Etapa
O objetivo deste passo é construir um tradutor dirigido por sintaxe que converte expressões aritméticas da **notação infixada** (operadores entre os operandos, ex: `9 - 5 + 2`) para a **notação pós-fixada** (operadores após os operandos, ex: `9 5 - 2 +`).

Para simplificar a compreensão inicial do *front-end* do compilador, este tradutor lê a entrada **caractere por caractere**, assumindo que cada número possui apenas um dígito (ex: `0` a `9`). Não há um analisador léxico (Scanner) nesta etapa.

## 2. Tradução Dirigida por Sintaxe
A técnica consiste em anexar ações (códigos) diretamente às regras da gramática. Assim que o analisador reconhece uma regra gramatical válida, ele executa a ação associada. 
No nosso código, as ações são os comandos `System.out.println("add")` (em Java) ou `fmt.Println("add")` (em Golang) que imprimem a operação assim que os operandos são identificados.

## 3. Analisador Sintático Preditivo Recursivo (Recursive Descent Parser)
Construímos um reconhecedor *top-down* (descendente). A regra principal é que **cada símbolo não-terminal da gramática vira uma função/método no código**:
- A regra `expr` virou o método `expr()`.
- A regra `digit` virou o método `digit()`.
- A regra `oper` virou o método `oper()`.

## 4. O Problema da Recursão à Esquerda
Uma gramática natural para expressões matemáticas seria:
`expr -> expr + digit | digit`

No entanto, se traduzíssemos isso diretamente para código, o método `expr()` chamaria `expr()` na primeira linha, criando um **loop infinito** (*Stack Overflow*). 
Para resolver isso, a gramática foi reescrita removendo a recursividade à esquerda:
```text
expr  -> digit oper
oper  -> + digit oper 
       | - digit oper 
       | ε (vazio)
digit -> 0 | 1 | ... | 9