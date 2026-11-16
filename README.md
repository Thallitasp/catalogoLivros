# 📚 Sistema de Gerenciamento de Catálogo de Livros em Java

Aplicação desenvolvida em Java para a **Atividade Prática 1** (Manipulação Avançada de Coleções e Tratamento de Erros), demonstrando o uso integrado de `HashMap`, `ArrayList`, `HashSet`, serialização de objetos e exceções personalizadas.

---

## 🎯 Funcionalidades e Requisitos Atendidos

- **Estruturas de Dados (`java.util.*`):**
  - `HashMap<String, Livro>`: Mapeia cada livro pelo seu identificador único (**ISBN**) para consultas diretas em tempo constante médio $O(1)$.
  - `ArrayList<Livro>`: Mantém a coleção ordenada dos objetos `Livro` para listagem e serialização.
  - `HashSet<String>`: Valida a unicidade do ISBN antes da inserção no `HashMap`, impedindo registros duplicados.
- **Operações CRUD e Persistência:**
  - Cadastro, busca e remoção de livros por ISBN.
  - Salvamento (`ObjectOutputStream`) e carregamento (`ObjectInputStream`) do acervo em arquivo binário (`catalogo_livros.dat`) via interface `Serializable`.
- **Tratamento Robusto de Erros:**
  - Exceções personalizadas (`LivroJaExisteException` e `LivroNaoEncontradoException`) para regras de domínio.
  - Tratamento de `FileNotFoundException`, `IOException` e `ClassNotFoundException` com blocos `try-with-resources`.

---

## 📂 Estrutura do Projeto

text
src/
├── exception/
│   ├── LivroJaExisteException.java         # Exceção lançada ao tentar cadastrar ISBN duplicado
│   └── LivroNaoEncontradoException.java    # Exceção lançada ao buscar ou remover ISBN inexistente
│
├── model/
│   └── Livro.java                          # Entidade Serializável representando um livro
│
├── service/
│   └── CatalogoLivrosService.java          # Regras de negócio, coleções e serialização em arquivo
│
└── application/
    └── Main.java                           # Classe principal com cenários de teste e tratamento de erros


---

## 🚀 Como Executar no Terminal

1. **Clone o repositório:**
   bash
   git clone https://github.com/Thallitasp/catalogoLivros.git
   cd catalogoLivros

2. **Compile os arquivos:**
   bash
   javac -d bin src/exception/*.java src/model/*.java src/service/*.java src/application/*.java
   

3. **Execute a aplicação:**
   bash
   java -cp bin application.Main

---

## 👩‍💻 Autora

**Thallita da Silva Pedreira**  
GitHub: [@Thallitasp](https://github.com/Thallitasp)
