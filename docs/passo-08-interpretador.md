# 📖 Estudos - Passo 8: Um Simples Interpretador

**Branch:** `passo-08-interpretador`  
**Material de Referência:** Estudo de Compiladores com Java e Golang[cite: 1]

## 1. Objetivo da Etapa
Finalizar o ciclo de vida do nosso tradutor acoplando um Interpretador (Back-end) ao processo de Tradução Dirigida por Sintaxe (Front-end)[cite: 1, 3]. Nosso sistema agora converte a linguagem fonte (Jack-like) para a notação pós-fixada (bytecode)[cite: 1] e imediatamente executa o programa em uma Máquina Virtual baseada em Pilha.

## 2. Componentes da Execução
*   **A Classe `Command`:** Representa a abstração de uma instrução pós-fixada. Ela lê a string gerada pelo `Parser` (ex: `"push 10"`) e a converte em um enum (`Command.Type.PUSH`) e um argumento (`"10"`).
*   **O Interpretador (Stack Machine):** 
    *   **Pilha de Execução (`Stack<Integer>`):** Onde os cálculos são resolvidos. Por estar em notação pós-fixada[cite: 1], qualquer operador matemático aciona o desempilhamento (`pop`) dos dois últimos valores, realiza a conta, e empilha (`push`) o resultado de volta.
    *   **Memória (`HashMap`):** Mapeia o nome das variáveis para os seus valores atuais, permitindo comandos de atribuição e recuperação (`pop a`, `push a`).

## 3. Integração (Parser -> Interpretador)
Para que as duas partes do compilador conversassem perfeitamente, o `Parser` foi refatorado. Onde antes usávamos fragmentos de código com `System.out.println` diretamente nas regras gramaticais (as ações semânticas)[cite: 1], agora acumulamos as *strings* em um `StringBuilder` e as retornamos de uma só vez através do método `p.output()`.

## 4. Anotações Pessoais / Dúvidas
- [x] Criar as classes `Command` e `Interpretador`.
- [x] Atualizar o Parser com o padrão de acumulação de String (`emit(String text)`).
- [x] Integrar todo o fluxo no `Main`, verificando se a saída imprime o resultado numérico exato no console.
- [x] Manter as opções matemáticas extras suportadas.