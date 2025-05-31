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

    @GetMapping("/descargar")
    public ResponseEntity<byte[]> descargarLibro(@RequestParam String archivo) {
        try {
            byte[] contenido = libroService.descargarArchivoEPUB(archivo);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archivo + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(contenido);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


}
