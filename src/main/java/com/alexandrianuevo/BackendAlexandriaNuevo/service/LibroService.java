package com.alexandrianuevo.BackendAlexandriaNuevo.service;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Libro;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.LibroRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.response.LibroResponse;
import com.alexandrianuevo.BackendAlexandriaNuevo.util.LibroComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<LibroResponse> buscarPorTituloOAutor(String texto) {
        List<Libro> libros = libroRepository.findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(texto, texto);
        libros.sort(new LibroComparator(texto));
        return libros.stream()
                .map(libro -> new LibroResponse(libro.getId(), libro.getTitulo(), libro.getAutor()))
                .toList();
    }

    public List<LibroResponse> obtenerTodos() {
        return libroRepository.findAll().stream()
                .map(libro -> new LibroResponse(libro.getId(), libro.getTitulo(), libro.getAutor()))
                .toList();
    }

    public String obtenerNombreArchivo(Long idLibro) {
        return libroRepository.findById(idLibro)
                .map(Libro::getArchivoUrl)
                .orElse(null);
    }

}

