package com.alexandrianuevo.BackendAlexandriaNuevo.service;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Biblioteca;
import com.alexandrianuevo.BackendAlexandriaNuevo.model.Libro;
import com.alexandrianuevo.BackendAlexandriaNuevo.model.Usuario;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.BibliotecaRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Autowired
    private LibroRepository libroRepository;

    // Guardar un nuevo registro en la biblioteca (lectura o favorito)
    public Biblioteca guardarRegistro(Usuario usuario, Libro libro, boolean enLectura, boolean esFavorito, String anotaciones) {
        Biblioteca registro = new Biblioteca();
        registro.setUsuarioId(usuario.getId());
        registro.setLibroId(libro.getId());
        registro.setEsFavorito(esFavorito);
        registro.setEnLectura(enLectura);
        registro.setAnotaciones(anotaciones);
        registro.setFecha(LocalDateTime.now());
        return bibliotecaRepository.save(registro);
    }

    // FAVORITOS
    public List<Libro> obtenerFavoritos(Long usuarioId) {
        List<Biblioteca> registros = bibliotecaRepository.findByUsuarioIdAndEsFavorito(usuarioId, true);
        List<Libro> libros = new ArrayList<>();

        for (Biblioteca registro : registros) {
            libros.add(libroRepository.findById(registro.getLibroId()).get());
        }

        return libros;
    }

    // LECTURAS
    public List<Libro> obtenerLecturas(Long usuarioId) {
        List<Biblioteca> registros = bibliotecaRepository.findByUsuarioIdAndEnLectura(usuarioId, true);
        List<Libro> libros = new ArrayList<>();

        for (Biblioteca registro : registros) {
            libros.add(libroRepository.findById(registro.getLibroId()).get());
        }

        return libros;
    }
}

