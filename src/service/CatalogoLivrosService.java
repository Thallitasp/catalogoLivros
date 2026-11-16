package service;

import exception.LivroJaExisteException;
import exception.LivroNaoEncontradoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.Livro;

public class CatalogoLivrosService {

    private Map<String, Livro> mapaLivrosPorIsbn = new HashMap<>();
    private List<Livro> listaLivros = new ArrayList<>();
    private Set<String> isbnsCadastrados = new HashSet<>();

    public void adicionarLivro(Livro livro) throws LivroJaExisteException {
        
        if (livro == null || livro.getIsbn() == null || livro.getIsbn().isBlank()) {
            throw new IllegalArgumentException("Os dados do livro e o ISBN não podem ser vazios.");
        }

        // Set.add() retorna false caso o ISBN já exista
        if (!isbnsCadastrados.add(livro.getIsbn())) {
            throw new LivroJaExisteException("Falha ao adicionar: já existe um livro cadastrado com o ISBN " + livro.getIsbn());
        }

        mapaLivrosPorIsbn.put(livro.getIsbn(), livro);
        listaLivros.add(livro);
    }

    // Remove livro por ISBN lançando exceção personalizada caso não exista
    public void removerLivro(String isbn) throws LivroNaoEncontradoException {

        if (!isbnsCadastrados.contains(isbn)) {
            throw new LivroNaoEncontradoException("Falha ao remover: nenhum livro encontrado com o ISBN " + isbn);
        }

        Livro livroRemovido = mapaLivrosPorIsbn.remove(isbn);
        isbnsCadastrados.remove(isbn);
        listaLivros.remove(livroRemovido);
    }

    public Livro buscarPorIsbn(String isbn) throws LivroNaoEncontradoException {
        
        Livro livro = mapaLivrosPorIsbn.get(isbn);

        if (livro == null) {
            throw new LivroNaoEncontradoException("Busca sem resultados: ISBN " + isbn + " não encontrado no catálogo.");
        }

        return livro;
    }

    public List<Livro> listarLivros() {
        return new ArrayList<>(listaLivros);
    }

    public void salvarCatalogo(String caminhoArquivo) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(listaLivros);
            System.out.println("[I/O] Catálogo salvo com sucesso em: " + caminhoArquivo);
        } catch (FileNotFoundException e) {
            System.err.println("[Erro de Arquivo] Caminho inválido ou arquivo não pôde ser criado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("[Erro de I/O] Falha ao serializar os dados do catálogo: " + e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    public void carregarCatalogo(String caminhoArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {

            List<Livro> livrosCarregados = (ArrayList<Livro>) ois.readObject();

            mapaLivrosPorIsbn.clear();
            listaLivros.clear();
            isbnsCadastrados.clear();

            for (Livro livro : livrosCarregados) {
                isbnsCadastrados.add(livro.getIsbn());
                mapaLivrosPorIsbn.put(livro.getIsbn(), livro);
                listaLivros.add(livro);
            }

            System.out.println("[I/O] Catálogo carregado com sucesso (" + listaLivros.size() + " livros restaurados).");

        } catch (FileNotFoundException e) {
            System.err.println("[Erro de Arquivo] Arquivo de catálogo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("[Erro de I/O] Falha ao ler o arquivo serializado: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("[Erro de Desserialização] Classe Livro incompatível ou não encontrada: " + e.getMessage());
        }
    }
    
}
