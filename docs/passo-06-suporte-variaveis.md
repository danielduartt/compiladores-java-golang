# 📖 Estudos - Passo 6: Suporte a Variáveis

**Branch:** `passo-06-suporte-variaveis`  
**Material de Referência:** Estudo de Compiladores com Java e Golang[cite: 1]

## 1. Objetivo da Etapa
O Passo 6 introduz o suporte a identificadores (nomes de variáveis) e palavras reservadas. O objetivo é permitir que o nosso compilador traduza sentenças de atribuição do tipo `let a = 42 + 5 - 8;`, algo comum na linguagem de estudo Jack[cite: 3].

## 2. Atualizações no Analisador Léxico (Scanner)
Para suportar o texto (letras e sublinhados), realizamos as seguintes inclusões:
*   **Novos Tokens:** Adição de `IDENT` (para variáveis), `LET` (palavra chave), `EQ` (símbolo `=`) e `SEMICOLON` (símbolo `;`).
*   **Função `identifier()`:** O Scanner usa as funções auxiliares `isAlpha` e `isAlphaNumeric` para agrupar todas as letras e números subsequentes a uma letra inicial, formando a string do identificador.
*   **Tabela de Palavras Reservadas:** Como `let` seria lido como um identificador comum (já que é composto por letras), criamos um `HashMap` (chamado `keywords`) que valida o lexema lido. Se a palavra extraída existir no mapa, o token gerado será um `TokenType.LET`, caso contrário, será um `TokenType.IDENT`.

## 3. Atualizações no Analisador Sintático (Parser)
Para que o `Parser` aceite misturar números e variáveis nas contas (ex: `preco + 5`), a gramática foi reescrita introduzindo a regra `term` (termo):
```text
expr -> term oper
oper -> + term oper | - term oper | ε 
term -> number | identifier
number -> [0-9]+