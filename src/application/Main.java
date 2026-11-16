package application;

import exception.LivroJaExisteException;
import exception.LivroNaoEncontradoException;
import model.Livro;
import service.CatalogoLivrosService;

public class Main {
    public static void main(String[] args) {

        CatalogoLivrosService catalogo = new CatalogoLivrosService();
        String arquivoCatalogo = "catalogo_livros.dat";

        System.out.println("=== 1. ADICIONANDO LIVROS E VALIDANDO DUPLICIDADE (SET + HASHMAP) ===");
        try {
            catalogo.adicionarLivro(new Livro("978-8576082675", "Codigo Limpo", "Robert C. Martin", 2009));
            catalogo.adicionarLivro(new Livro("978-8550804620", "Java Efetivo", "Joshua Bloch", 2019));
            catalogo.adicionarLivro(new Livro("978-8573076103", "Padroes de Projetos", "Erich Gamma et al.", 2000));
            System.out.println("Livros iniciais cadastrados com sucesso!");

            catalogo.adicionarLivro(new Livro("978-8576082675", "Codigo Limpo (Duplicado)", "Robert C. Martin", 2009));

        } catch (LivroJaExisteException e) {
            System.err.println("[Excecao Personalizada]: " + e.getMessage());
        }

        System.out.println("\n=== 2. BUSCA E REMOCAO POR ISBN ===");
        
        try {

            Livro encontrado = catalogo.buscarPorIsbn("978-8550804620");
            System.out.println("Livro encontrado: " + encontrado);

            catalogo.removerLivro("978-8573076103");
            System.out.println("Livro ISBN 978-8573076103 removido com sucesso.");

            catalogo.removerLivro("000-0000000000");

        } catch (LivroNaoEncontradoException e) {
            System.err.println("[Excecao Personalizada]: " + e.getMessage());
        }

        System.out.println("\n=== 3. PERSISTENCIA VIA SERIALIZACAO (I/O) ===");
        catalogo.salvarCatalogo(arquivoCatalogo);

        CatalogoLivrosService novoCatalogo = new CatalogoLivrosService();
        novoCatalogo.carregarCatalogo(arquivoCatalogo);

        System.out.println("\n--- Acervo Restaurado do Arquivo ---");
        
        for (Livro l : novoCatalogo.listarLivros()) {
            System.out.println(l);
        }

        System.out.println("\n=== 4. TESTE DE ERRO DE I/O (ARQUIVO INEXISTENTE) ===");
        novoCatalogo.carregarCatalogo("arquivo_inexistente.dat");
    }

}
