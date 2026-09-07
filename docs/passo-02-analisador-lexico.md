# 📖 Estudos - Passo 2: Analisador Léxico (Scanner)

**Branch:** `passo-02-analisador-lexico`  
**Material de Referência:** Estudo de Compiladores com Java e Golang 

## 1. O Problema do Passo Anterior
No Passo 1, construímos um tradutor simples que lia a entrada caractere por caractere. Isso funcionava bem para números de apenas um dígito (ex: `8 + 5`). 
No entanto, expressões matemáticas reais contêm números maiores (ex: `45 + 89 - 876`). Se tentássemos passar `45` no código antigo, ele geraria um erro sintático ao ler o `5`, pois esperava um operador lógico após o `4`.

## 2. A Solução: O Analisador Léxico (Scanner)
Para suportar números com múltiplos dígitos e, futuramente, variáveis e palavras reservadas, precisamos do **Analisador Léxico**.
Sua responsabilidade é transformar o fluxo de caracteres soltos em um fluxo de palavras com significado, classificando-as em categorias sintáticas.

### Lexemas e Tokens
- **Lexema:** É a palavra em si, a sequência de caracteres (ex: `"45"`, `"+"`, `"custo"`).
- **Token:** É o objeto que encapsula o lexema e a sua "classe gramatical" ou categoria (ex: `NUMBER`, `PLUS`, `IDENTIFIER`).

## 3. Abordagem de Implementação (Solução Ad-hoc)
Existem ferramentas automáticas (como Flex e Lex) e geradores baseados em tabelas para criar Scanners. No entanto, em compiladores modernos de produção (como os de Golang, TypeScript e Rust) é muito comum o uso da **Solução Ad-hoc**.

Nesta etapa, implementamos um Scanner manual:
1. **Ignorando espaços:** Implementamos o método `skipWhitespace()` para descartar espaços em branco, que não têm valor semântico em expressões matemáticas.
2. **Agrupando dígitos:** Usamos um laço `while` (ou `for` em Go) que verifica se o caractere atual é um dígito. Se for, ele continua concatenando os próximos caracteres até encontrar um símbolo diferente (como um espaço ou operador), formando assim um lexema completo para o token `NUMBER`.
3. **Mapeamento de Operadores:** Identificação direta via `switch/case` para os símbolos `+`, `-`, `*` e `/`.

## 4. Estrutura do Código
- **`TokenType` (Java/Go):** Enumeração/Constantes definindo os tipos de tokens aceitos (`NUMBER`, `PLUS`, `MINUS`, `MULT`, `DIV`, `EOF`, `ILLEGAL`).
- **`Token` (Java/Go):** Classe/Struct que guarda o tipo e o valor em texto do lexema.
- **`Scanner` (Java/Go):** Classe que recebe o array de bytes, varre o texto e fornece o método `nextToken()` para extrair a próxima unidade léxica válida.

## 5. Anotações Pessoais / Dúvidas
- [x] Implementar ignorador de espaços em branco.
- [x] Agrupar múltiplos dígitos em um único token `NUMBER`.
- [x] Identificar e tipar operadores matemáticos essenciais.
- [ ] Como o Parser (do Passo 1) vai consumir esses Tokens no próximo passo?