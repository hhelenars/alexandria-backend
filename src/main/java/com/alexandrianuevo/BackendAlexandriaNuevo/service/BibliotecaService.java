package com.alexandrianuevo.BackendAlexandriaNuevo.service;

import com.alexandrianuevo.BackendAlexandriaNuevo.anotaciones.Anotacion;
import com.alexandrianuevo.BackendAlexandriaNuevo.anotaciones.Subrayado;
import com.alexandrianuevo.BackendAlexandriaNuevo.model.Biblioteca;
import com.alexandrianuevo.BackendAlexandriaNuevo.model.Libro;
import com.alexandrianuevo.BackendAlexandriaNuevo.model.Usuario;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.BibliotecaRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.LibroRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.response.LibroResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Autowired
    private LibroRepository libroRepository;



    // FAVORITOS
    public List<LibroResponse> obtenerFavoritos(Long usuarioId) {
        List<Biblioteca> registros = bibliotecaRepository.findByUsuarioIdAndEsFavorito(usuarioId, true);
        List<LibroResponse> favoritos = new ArrayList<>();

        for (Biblioteca registro : registros) {
            libroRepository.findById(registro.getLibroId()).ifPresent(libro -> {
                favoritos.add(new LibroResponse(libro.getId(), libro.getTitulo(), libro.getAutor()));
            });
        }
        return favoritos;
    }

    // LECTURAS
    public List<LibroResponse> obtenerLecturas(Long usuarioId) {
        List<Biblioteca> registros = bibliotecaRepository.findByUsuarioIdAndEnLectura(usuarioId, true);
        List<LibroResponse> lecturas = new ArrayList<>();


        for (Biblioteca registro : registros) {
            libroRepository.findById(registro.getLibroId()).ifPresent(libro -> {
                lecturas.add(new LibroResponse(libro.getId(), libro.getTitulo(), libro.getAutor()));
            });
            System.out.println(registro.getId());
        }
        if (lecturas.isEmpty())
            System.out.println("No hay nada en lecturas");

        return lecturas;
    }
}

