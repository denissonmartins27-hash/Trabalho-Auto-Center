# 📋 README — Diário da IA
## Sistema de Gestão Auto Center Rota 381
**Disciplina:** Desenvolvimento de Programas Estruturados e Modularização  
**Professor:** Raffael Carvalho  

**Autores:**
- Alice Alberti Silva
- Bianca Paiva Faria
- Denisson Martins
- Gabriel Figueiredo Bueno

---

## 1. Quais ferramentas, métodos ou bibliotecas proibidas a IA tentou incluir no código nas primeiras tentativas?

Nas primeiras interações com a IA (Claude), foram identificadas as seguintes tentativas de uso de estruturas **não permitidas pela disciplina**:

- **`ArrayList` e `List<T>`**: A IA tentou utilizar coleções dinâmicas da biblioteca `java.util.*` para armazenar mecânicos, veículos e peças, ao invés de vetores de tamanho fixo.
- **Métodos dentro das classes de dados**: A IA sugeriu adicionar métodos como `toString()` e construtores parametrizados dentro das classes `Mecanico`, `Veiculo`, `Peca` e `OrdemServico`, o que caracteriza uso de Orientação a Objetos não permitido.
- **`HashMap`**: Para a busca de peças e mecânicos por código, a IA tentou usar `HashMap<Integer, Peca>` para facilitar a localização por chave, estrutura também proibida.
- **`java.time.LocalDateTime`**: Para registrar a data e hora das Ordens de Serviço, a IA sugeriu o uso dessa biblioteca sem que o grupo tivesse solicitado.

O grupo identificou todas essas ocorrências, recusou o código gerado e solicitou a refatoração para o padrão exigido pela disciplina.

---

## 2. Como foi o prompt que o grupo usou para obrigar a IA a refatorar o código para o padrão estruturado com vetores fixos?

O grupo utilizou o seguinte prompt para forçar a IA a respeitar as restrições técnicas da disciplina:

> *"Refaça o código usando APENAS vetores de tamanho fixo (ex: `Mecanico[] mecanicos = new Mecanico[100]`), laços `for` e `while` tradicionais, e classes simples sem métodos. É estritamente proibido usar ArrayList, List, HashMap, java.util.*, Orientação a Objetos avançada ou banco de dados. O sistema deve ser 100% estruturado e modularizado em arquivos .java separados, cada um com uma responsabilidade."*

Com esse prompt, a IA passou a gerar o código dentro dos padrões exigidos, utilizando:
- Vetores fixos com tamanho `MAX = 100`
- Contadores inteiros (`totalMecanicos`, `totalVeiculos`, etc.)
- Laços `for` para busca e validação
- Classes simples apenas com atributos (`public class Mecanico`, `public class Peca`, etc.)

---

## 3. Qual regra de negócio a IA se enrolou para fazer e o grupo precisou arrumar ou debugar manualmente?

### Problema: Atualização do estoque após abertura de OS

A regra de negócio mais difícil foi a **validação e desconto automático do estoque ao abrir uma Ordem de Serviço**. A IA gerou o código de forma incorreta em dois pontos:

**Problema 1 — Passagem de parâmetros:**  
A IA passou o total de peças como `int` simples para os métodos dos módulos. Como Java passa inteiros por valor (não por referência), as alterações feitas dentro do método não refletiam no vetor original. O grupo precisou refatorar para usar `int[]` (vetor de tamanho 1) para simular passagem por referência:

```java
// Errado (gerado pela IA inicialmente)
static void reporEstoque(Scanner sc, Peca[] pecas, int totalPecas)

// Corrigido pelo grupo
static void reporEstoque(Scanner sc, Peca[] pecas, int[] totalPecas)
```

**Problema 2 — Salvamento do arquivo CSV:**  
Após o desconto do estoque na OS, o arquivo `pecas.csv` não era atualizado corretamente porque o método `salvarPecas` estava sendo chamado com o valor antigo do total. O grupo identificou o bug e corrigiu a chamada para passar `totalPecas[0]` em vez de `totalPecas`.

Essas correções foram feitas manualmente pelo grupo após testes práticos no Eclipse, sem auxílio da IA.

---

## 📁 Estrutura do Projeto

```
autocenter/
├── AutoCenter.java       → Menu principal e coordenação dos módulos
├── Mecanico.java         → Estrutura de dados do Mecânico
├── Veiculo.java          → Estrutura de dados do Veículo
├── Peca.java             → Estrutura de dados da Peça
├── OrdemServico.java     → Estrutura de dados da Ordem de Serviço
├── Cadastros.java        → Módulo de cadastros e reposição de estoque
├── OrdemDeServico.java   → Módulo de abertura de OS
├── Relatorios.java       → Módulo de relatórios e alerta de estoque
└── Arquivos.java         → Módulo de persistência em arquivos CSV
```

---

## ✅ Funcionalidades Implementadas

- Cadastro de Mecânicos, Veículos e Peças
- Abertura de Ordem de Serviço com validação de estoque
- Desconto automático de estoque ao abrir OS
- Persistência de dados em arquivos `.csv`
- Relatório de Comissão da Equipe
- Relatório de Inventário Crítico (peças zeradas)
- Relatório de Faturamento de Peças
- **Diferencial:** Reposição de estoque de peças (opção 6 do menu)