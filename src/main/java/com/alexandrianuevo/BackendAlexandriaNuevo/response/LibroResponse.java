package com.alexandrianuevo.BackendAlexandriaNuevo.response;

public class LibroResponse {
    private Long id;
    private String titulo;
    private String autor;
    private String categoria;

    public LibroResponse(Long id, String titulo, String autor, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }
}
