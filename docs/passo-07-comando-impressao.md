# 📖 Estudos - Passo 7: Comando de Impressão e Múltiplos Comandos

**Branch:** `passo-07-comando-impressao`  

## 1. Objetivo da Etapa
Até o Passo 6, nosso compilador era capaz de processar apenas um único comando por execução. O objetivo desta etapa é transformar o analisador em um sistema capaz de ler um programa completo, composto por múltiplas linhas de instrução, e adicionar o suporte ao comando de saída `print`.

## 2. A Evolução da Gramática
A introdução de múltiplos comandos exigiu a criação de um novo nível hierárquico no topo da nossa gramática. 
Em vez da execução começar diretamente pela expressão matemática ou pela atribuição `let`, ela agora começa por uma coleção de comandos (statements). A gramática foi expandida para:

```text
statements     -> statement*
statement      -> printStatement | letStatement
printStatement -> 'print' expr ';'
letStatement   -> 'let' identifier '=' expr ';'