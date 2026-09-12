# 📖 Estudos - Passo 4: Suportando o Tipo de Token Number

**Branch:** `passo-04-suporte-number`  
**Material de Referência:** Estudo de Compiladores com Java e Golang[cite: 1]

## 1. Objetivo da Etapa
O objetivo do Passo 4 é permitir que o Analisador Léxico agrupe múltiplos dígitos consecutivos em uma única unidade estrutural (Token), representando números inteiros. Até o momento, considerávamos que cada caractere correspondia a um token isolado[cite: 3].

## 2. A Estrutura do Token
Um token não pode mais ser apenas um caractere (`char` ou `rune`). Ele precisa ser classificado. Por isso, implementamos as seguintes estruturas:
*   **`TokenType`:** Um enumerador (ou conjunto de constantes em Go) que mapeia todos os tipos válidos de palavras na nossa linguagem (ex: `NUMBER`, `PLUS`, `MINUS`, `EOF`).
*   **`Token`:** Uma classe/struct que une duas informações cruciais:
    *   **Tipo (`type`):** A categoria sintática (ex: `NUMBER`).
    *   **Lexema (`lexeme`):** A sequência exata de caracteres que foi lida (ex: `"45"`).

O método `toString()` do Token foi formatado para exibir `<TIPO>lexema</TIPO>`, facilitando a visualização da análise léxica durante os testes no método `Main`.

## 3. Lógica de Identificação de Números
Em vez de depender de expressões regulares complexas ou geradores automáticos, optamos por uma implementação manual (Ad-hoc) usando um autômato finito no método `nextToken()`. 

A lógica segue a seguinte regra:
1.  O Scanner olha o caractere atual (`peek()`).
2.  Se o caractere for um dígito (avaliado por `Character.isDigit()` em Java ou `unicode.IsDigit()` em Go), a função auxiliar `number()` é acionada.
3.  A função `number()` salva o índice inicial (`start`) e entra em um laço de repetição (`while` ou `for`), avançando o ponteiro de leitura enquanto os próximos caracteres continuarem sendo dígitos.
4.  Quando o laço é interrompido (por um operador ou espaço em branco), a substring compreendida entre o início (`start`) e o índice atual (`current`) é extraída e devolvida como um novo `Token(TokenType.NUMBER, lexema)`.

## 4. O Impacto no Parser
Como alteramos a saída do Scanner (que antes retornava `char` e agora retorna um objeto `Token`), **o `Parser` do Passo 3 está quebrado nesta branch**. O método `Main` foi adaptado temporariamente para interagir apenas com o Scanner, imprimindo o fluxo de tokens gerado para a expressão matemática. 
A correção e a adaptação do Parser para consumir a nova estrutura de tokens será o foco exclusivo do Passo 5[cite: 3].

## 5. Anotações Pessoais / Dúvidas
- [x] Criar as estruturas de Token e TokenType.
- [x] Adaptar o Scanner para retornar objetos Token.
- [x] Implementar a lógica Ad-hoc de leitura de múltiplos dígitos (`[0-9]+`).
- [ ] Entender como o caso de números com múltiplos zeros no início (ex: `000`) se divide em três tokens `NUMBER` de valor `0`.