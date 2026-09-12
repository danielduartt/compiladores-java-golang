# 📖 Estudos - Passo 3: Refatorando o Tradutor

**Branch:** `passo-03-refatorando-tradutor`  
**Material de Referência:** Estudo de Compiladores com Java e Golang 

## 1. Objetivo da Etapa
O foco desta etapa é cumprir o passo "3. Refatorando o tradutor: incluindo o analisador léxico"[cite: 2]. O objetivo não é mudar a saída do programa, mas sim organizar a arquitetura do código. Retiramos a responsabilidade de ler e gerenciar os caracteres de entrada do nosso Parser e delegamos esse trabalho exclusivamente ao novo Scanner.

## 2. O que mudou?
Neste passo, mantivemos o objetivo principal de produzir a notação pós-fixada, onde os operadores aparecem após os operandos[cite: 1]. Contudo, a estrutura interna evoluiu:

*   **Delegação de Leitura:** O `Parser` não manipula mais arrays de bytes nem controla índices (`current`). Ele confia totalmente no `Scanner` para receber o caractere atual através do método `nextToken()`.
*   **Manutenção do Analisador Preditivo:** O código continua funcionando como um analisador sintático preditivo recursivo, baseado na técnica de construção descendente[cite: 1].
*   **Preservação das Ações Semânticas:** O tradutor dirigido por sintaxe ainda associa fragmentos de código às produções da gramática, executando as ações semânticas (como imprimir "add") no momento exato em que os tokens são validados[cite: 2].

## 3. Como a Arquitetura Ficou Dividida
1.  **`Scanner` (Java/Go):** Agora é o único componente que tem contato direto com a string fornecida pelo usuário. Para fins didáticos desta refatoração, ele foi temporariamente simplificado para retornar apenas um caractere.
2.  **`Parser` (Java/Go):** Ficou mais limpo e focado estritamente em checar a validade gramatical e acionar a tradução. 

## 4. Anotações Pessoais / Dúvidas
> *(Use este espaço para anotar detalhes da sua implementação ou dúvidas das aulas)*
- [x] Remover os métodos `peek` e variáveis de controle de leitura de dentro do Parser.
- [x] Fazer o Parser inicializar o Scanner em seu construtor.
- [x] Confirmar que a tradução pós-fixada de `8+5-7*9` continua imprimindo exatamente o mesmo resultado do Passo 1.