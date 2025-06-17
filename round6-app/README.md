# Round 6 - Gerenciador de Reality Show

## Descrição
Este é um sistema de gerenciamento para o Reality Show "Round 6" desenvolvido em Java com interface gráfica Swing. O sistema permite cadastrar e gerenciar participantes, locais e desafios do reality show.

## Funcionalidades

### 1. Cadastro de Locais
- Nome do local
- Capacidade máxima de pessoas
- Edição e exclusão de locais
- Validação para evitar duplicatas

### 2. Cadastro de Participantes
- Nome do participante
- Número único do participante
- Idade (deve ser positiva)
- Edição e exclusão de participantes
- Validação para evitar números duplicados

### 3. Cadastro de Desafios
- Nome do desafio
- Número mínimo de participantes para iniciar
- Número de participantes que passam ao final
- Lista de regras (texto livre)
- Local de realização (selecionado da lista de locais cadastrados)
- Limite máximo de 5 desafios
- Validação de capacidade do local vs. número mínimo de participantes
- Edição e exclusão de desafios

### 4. Interface Gráfica
- Interface com abas para cada tipo de cadastro
- Tabelas para visualização dos dados cadastrados
- Formulários para entrada de dados
- Botões para adicionar, editar, remover e limpar
- Validações com mensagens de erro e confirmação

## Requisitos do Sistema
- Java 11 ou superior
- Sistema operacional com suporte a interface gráfica (Windows, Linux com X11, macOS)

## Como Executar

### Compilação
```bash
javac *.java
```

### Execução
```bash
java Round6App
```

## Estrutura do Projeto

### Classes Principais
- **Local.java**: Representa um local onde os desafios podem ser realizados
- **Participante.java**: Representa um participante do reality show
- **Desafio.java**: Representa um desafio do reality show
- **GerenciadorRound6.java**: Classe de negócio que gerencia todas as operações
- **Round6App.java**: Interface gráfica principal da aplicação

### Validações Implementadas
- Campos obrigatórios não podem estar vazios
- Números devem ser positivos
- Não é permitido duplicar nomes de locais ou desafios
- Não é permitido duplicar números de participantes
- Local deve ter capacidade suficiente para o número mínimo de participantes do desafio
- Máximo de 5 desafios podem ser cadastrados
- Locais em uso por desafios não podem ser removidos

## Dados de Exemplo
O sistema inicia com alguns dados de exemplo:
- 3 locais pré-cadastrados
- 3 participantes pré-cadastrados

## Desenvolvido por
Sistema desenvolvido conforme especificações da avaliação A3 de Programação de Soluções Computacionais.

