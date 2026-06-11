# 🚗 AutoCenter Rota 381

**Autores:**
- Alice Alberti Silva
- Bianca Paiva Faria
- Denisson Martins
- Gabriel Figueiredo Bueno

---

Sistema de gerenciamento para oficina mecânica desenvolvido em Java, com persistência de dados em arquivos CSV.

---

## 📋 Descrição

O **AutoCenter Rota 381** é um sistema de linha de comando (console) que permite gerenciar as operações de uma oficina mecânica: cadastro de mecânicos, veículos e peças, abertura de ordens de serviço, controle de estoque e geração de relatórios financeiros.

---

## 🗂️ Estrutura do Projeto

```
autocenter/
│
├── Dados.java            # Classes de dados (entidades do sistema)
├── AutoCenter.java       # Classe principal – menu e coordenação
├── Arquivos.java         # Leitura e gravação dos arquivos CSV
├── Cadastros.java        # Cadastro de mecânicos, veículos e peças
├── OrdemDeServico.java   # Abertura e gestão das ordens de serviço
├── Relatorios.java       # Relatórios e alertas de estoque
│
├── mecanicos.csv         # Gerado automaticamente pelo sistema
├── veiculos.csv          # Gerado automaticamente pelo sistema
├── pecas.csv             # Gerado automaticamente pelo sistema
└── ordens.csv            # Gerado automaticamente pelo sistema
```

---

## 🧩 Módulos

### `Dados.java`
Define as **entidades (structs)** utilizadas pelo sistema:

| Classe | Campos |
|---|---|
| `Mecanico` | `codigo`, `nome`, `especialidade` |
| `Veiculo` | `placa`, `nomeDono`, `modelo` |
| `Peca` | `codigo`, `descricao`, `quantidade`, `precoUnitario` |
| `OrdemServico` | `numero`, `placaVeiculo`, `codigoMecanico`, `codigoPeca`, `quantidadePeca`, `valorMaoDeObra` |

---

### `AutoCenter.java`
Classe principal com o **menu interativo** no console. Responsável por:
- Carregar todos os dados do disco na inicialização
- Exibir alerta automático de estoque baixo a cada loop
- Coordenar a chamada dos demais módulos

---

### `Arquivos.java`
Gerencia a **persistência dos dados** em arquivos `.csv`:
- `salvar*` → grava o array em disco após cada alteração
- `carregar*` → lê o arquivo CSV e popula o array ao iniciar o sistema
- Arquivos gerados automaticamente; a ausência deles na primeira execução é tratada sem erro

---

### `Cadastros.java`
Contém os métodos de **cadastro e manutenção**:
- `cadastrarMecanico` – valida código duplicado antes de inserir
- `cadastrarVeiculo` – valida placa duplicada antes de inserir
- `cadastrarPeca` – valida código duplicado antes de inserir
- `reporEstoque` – localiza uma peça pelo código e incrementa o estoque

---

### `OrdemDeServico.java`
Gerencia a **abertura de uma OS**:
- Valida existência do veículo, mecânico e peça informados
- Verifica se há estoque suficiente para atender a OS
- Desconta automaticamente a quantidade do estoque ao salvar
- Exibe resumo com mão de obra, valor de peças e total geral

---

### `Relatorios.java`
Gera **três relatórios** e um alerta automático:

| # | Relatório | Descrição |
|---|---|---|
| 1 | Comissão da Equipe | Total de mão de obra por mecânico |
| 2 | Inventário Crítico | Peças com estoque zerado |
| 3 | Faturamento de Peças | Total faturado em peças por OS |
| — | Alerta de Estoque Baixo | Exibido automaticamente quando `quantidade ≤ 3` |

---

## ▶️ Como Executar

### Pré-requisitos
- Java JDK 8 ou superior instalado
- Terminal / Prompt de Comando

### Compilação

```bash
javac *.java
```

### Execução

```bash
java AutoCenter
```

> Os arquivos CSV são criados automaticamente na mesma pasta na primeira vez que dados são salvos.

---

## 💾 Persistência de Dados

Os dados são salvos em arquivos CSV separados por ponto e vírgula (`;`). Exemplo do formato de `ordens.csv`:

```
1;ABC1234;101;201;2;150.00
2;DEF5678;102;203;1;200.00
```

Cada arquivo é **regravado por completo** após qualquer alteração, garantindo consistência.

---

## ✅ Funcionalidades

- [x] Cadastro de mecânicos com validação de código duplicado
- [x] Cadastro de veículos com validação de placa duplicada
- [x] Cadastro de peças com validação de código duplicado
- [x] Reposição de estoque
- [x] Abertura de Ordem de Serviço com desconto automático de estoque
- [x] Alerta visual de estoque baixo (≤ 3 unidades) no menu principal
- [x] Relatório de comissão por mecânico
- [x] Relatório de inventário crítico (estoque zerado)
- [x] Relatório de faturamento de peças
- [x] Persistência total em arquivos CSV

---

## 🛠️ Tecnologias Utilizadas

- **Java** (sem frameworks externos)
- **CSV** para persistência de dados
- **Console / Terminal** como interface do usuário

---

## 👨‍💻 Autores

Desenvolvido como projeto acadêmico de sistema de gestão para oficina mecânica.

- Alice Alberti Silva
- Bianca Paiva Faria
- Denisson Martins
- Gabriel Figueiredo Bueno