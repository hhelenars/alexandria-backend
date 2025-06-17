package com.alexandrianuevo.BackendAlexandriaNuevo.controller;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Libro;
import com.alexandrianuevo.BackendAlexandriaNuevo.response.LibroResponse;
import com.alexandrianuevo.BackendAlexandriaNuevo.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // Buscar libros por título y autor
    @GetMapping("/buscar")
    public List<LibroResponse> buscarPorTituloOAutor(@RequestParam String texto) {
        return libroService.buscarPorTituloOAutor(texto);
    }

    // Obtener todos los libros
    @GetMapping("/todos")
    public List<LibroResponse> getTodos() {
        return libroService.obtenerTodos();
    }

    @GetMapping("/categorias")
    public List<String> obtenerCategorias() {
        return libroService.obtenerCategorias();
    }

    @GetMapping("/categorias/libros")
    public ResponseEntity<List<LibroResponse>> obtenerLibrosPorCategorias(@RequestParam String categoria) {
        List<LibroResponse> libros =  libroService.buscarPorCategoria(categoria);
        if (libros != null) {
            return ResponseEntity.ok(libros);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/archivo-url")
    public ResponseEntity<String> obtenerArchivoUrl(@RequestParam  Long id) {
        String archivoUrl = libroService.obtenerNombreArchivo(id);
        if (archivoUrl != null) {
            return ResponseEntity.ok(archivoUrl);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
