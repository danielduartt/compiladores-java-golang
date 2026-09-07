# Construção de Compiladores: Tradução Dirigida por Sintaxe

Este repositório contém as atividades e implementações práticas da disciplina de Compiladores. O desenvolvimento segue os passos de construção de um tradutor/interpretador, inspirado na linguagem Jack, utilizando as linguagens Java e Golang, com base no material de referência Estudo de Compiladores com Java e Golang .

## 🎯 Sobre o Projeto

O objetivo principal desta etapa inicial é compreender e aplicar os conceitos de **Tradução Dirigida por Sintaxe**. 
A tradução dirigida por sintaxe consiste em anexar regras ou fragmentos de código (ações semânticas) às produções de uma gramática. O compilador construído aqui evoluirá de um simples tradutor que lê caractere por caractere para um interpretador completo capaz de processar variáveis e operações matemáticas, culminando no uso de ferramentas geradoras de analisadores como o ANTLR.

## 🚀 Roadmap de Desenvolvimento

Abaixo estão as 9 etapas propostas para a evolução do nosso compilador. Conforme o progresso, os itens serão marcados.

- [x] 1. Um simples tradutor
- [x] 2. Analisador léxico (Scanner)
- [ ] 3. Refatorando o tradutor: incluindo o analisador léxico
- [ ] 4. Suportando o tipo de token `number`
- [ ] 5. Atualizando o Parser para suportar `number`
- [ ] 6. Atualizando o Scanner e Parser para suportar variáveis
- [ ] 7. Incluindo comando de impressão
- [ ] 8. Um simples interpretador
- [ ] 9. Usando Antlr

## 📁 Estrutura do Repositório

Como este é um projeto guiado pelo documento Estudo de Compiladores com Java e Golang , o código-fonte está dividido entre as duas linguagens de programação escolhidas:

* `/java`: Implementações feitas em Java.
* `/golang`: Implementações feitas em Go.
* `/docs`: Documentação adicional, anotações de aula e diagramas das Árvores de Derivação.

## 🛠️ Como Executar

*(Adicione aqui as instruções básicas para compilar e rodar os projetos quando você criar os primeiros códigos)*

**Para Java:**
```bash
cd java
javac src/main/java/compiler/Main.java
java src/main/java/compiler/Main