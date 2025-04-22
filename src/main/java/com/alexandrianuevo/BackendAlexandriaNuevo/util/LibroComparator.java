package com.alexandrianuevo.BackendAlexandriaNuevo.util;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Libro;

import java.util.Comparator;

public class LibroComparator implements Comparator<Libro> {

    @Override
    public int compare(Libro l1, Libro l2) {
        int tituloComp = l1.getTitulo().compareToIgnoreCase(l2.getTitulo());
        if (tituloComp != 0) {
            return tituloComp;
        }

        int autorComp = l1.getAutor().compareToIgnoreCase(l2.getAutor());
        if (autorComp != 0) {
            return autorComp;
        }

        return 0; // Si título y autor son iguales
    }
}

