# 📖 Estudos - Passo 5: Atualizando o Parser para Suportar Números

**Branch:** `passo-05-parser_number`  
**Material de Referência:** Estudo de Compiladores com Java e Golang[cite: 1]

## 1. Objetivo da Etapa
O objetivo do Passo 5 é religar o nosso Analisador Sintático (Parser) ao Analisador Léxico (Scanner), adaptando o Parser para consumir os novos objetos da classe `Token` e identificar as regras baseadas em `TokenType`[cite: 3]. Esta integração finaliza o suporte à tradução de expressões com números inteiros de múltiplos dígitos e espaços em branco[cite: 3].

## 2. Delegação da Limpeza (Espaços em Branco)
Em linguagens de programação, a maioria dos espaços e quebras de linha não possui relevância sintática. Durante esta etapa, delegamos a função de ignorar caracteres invisíveis (`' '`, `'\r'`, `'\t'`, `'\n'`) ao Scanner através do método `skipWhitespace()`[cite: 3]. O Scanner realiza essa limpeza antes de iniciar a leitura do próximo caractere válido[cite: 3]. 
Desta forma, uma expressão formatada como `"89 +508 -7+99"`, que antes geraria um erro léxico, agora é agrupada em tokens perfeitamente utilizáveis pelo Parser[cite: 3].

## 3. Adaptação do Analisador Preditivo
Como vimos, a essência do analisador preditivo descendente é o mapeamento de não-terminais para funções e a validação (*match*) de terminais[cite: 2]. Para suportar o token do tipo `number`, realizamos as seguintes alterações no `Parser`:

1.  **Mudança de Tipo:** A variável de controle `currentToken` passou a armazenar um objeto `Token`, abandonando o antigo tipo `char`.
2.  **Evolução do Match:** O método `match()` deixou de comparar caracteres fixos e passou a validar o tipo do token corrente (`currentToken.type == t`)[cite: 3].
3.  **Renomeação da Regra:** A regra gramatical `digit`, que aceitava apenas um caractere `0-9`, foi renomeada para `number` (`number -> [0-9]+`)[cite: 3]. No código, o método `digit()` foi substituído por `number()`, onde a ação semântica associada imprime o lexema numérico agrupado (`System.out.println("push " + currentToken.lexeme)`)[cite: 3].
4.  **Validação dos Operadores:** A regra `oper` foi refatorada para utilizar os validadores baseados em enumeração, como `TokenType.PLUS` e `TokenType.MINUS`[cite: 3].

## 4. O Fluxo Completo
Com essa atualização, a expressão infixada `"45  + 89   -       876"`[cite: 3] entra no compilador, passa pelo Scanner para remoção de espaços e agrupamento numérico, e chega ao Parser como uma sequência organizada de tokens[cite: 3]. O Parser preditivo processa a estrutura e, através de ações semânticas integradas, cospe a tradução correta para a notação pós-fixada[cite: 1, 3]:
```text
push 45
push 89
add
push 876
sub