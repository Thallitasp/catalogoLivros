package model;

import java.io.Serializable;

public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    private String isbn;
    private String titulo;
    private String autor;
    private int anoPublicacao;

    public Livro(String isbn, String titulo, String autor, int anoPublicacao) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    @Override
    public String toString() {
        return String.format("ISBN: %s | Título: '%s' | Autor: %s (%d)", isbn, titulo, autor, anoPublicacao);
    }
    
}
