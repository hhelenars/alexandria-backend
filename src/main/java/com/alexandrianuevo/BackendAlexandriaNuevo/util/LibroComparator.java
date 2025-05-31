package com.alexandrianuevo.BackendAlexandriaNuevo.util;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Libro;

import java.util.Comparator;
public class LibroComparator implements Comparator<Libro> {
    private final String textoBuscado;

    public LibroComparator(String textoBuscado) {
        this.textoBuscado = textoBuscado.toLowerCase();
    }

    @Override
    public int compare(Libro l1, Libro l2) {
        int r1 = calcularRelevancia(l1);
        int r2 = calcularRelevancia(l2);

        if (r1 == r2) {
            return l1.getTitulo().compareToIgnoreCase(l2.getTitulo());
        }

        return Integer.compare(r1, r2);
    }

    private int calcularRelevancia(Libro libro) {
        String titulo = libro.getTitulo().toLowerCase();
        String autor = libro.getAutor().toLowerCase();

        if (titulo.equals(textoBuscado) || autor.equals(textoBuscado)) return 0;
        if (titulo.startsWith(textoBuscado) || autor.startsWith(textoBuscado)) return 1;
        if (titulo.contains(textoBuscado) || autor.contains(textoBuscado)) return 2;
        return 3;
    }
}


