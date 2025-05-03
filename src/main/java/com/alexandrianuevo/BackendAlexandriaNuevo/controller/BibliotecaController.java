package com.alexandrianuevo.BackendAlexandriaNuevo.controller;

import com.alexandrianuevo.BackendAlexandriaNuevo.anotaciones.Anotacion;
import com.alexandrianuevo.BackendAlexandriaNuevo.anotaciones.Subrayado;
import com.alexandrianuevo.BackendAlexandriaNuevo.model.Biblioteca;
import com.alexandrianuevo.BackendAlexandriaNuevo.model.Libro;
import com.alexandrianuevo.BackendAlexandriaNuevo.model.Usuario;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.LibroRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.UsuarioRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/biblioteca")
@CrossOrigin(origins = "*")
public class BibliotecaController {
    @Autowired
    private BibliotecaService bibliotecaService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LibroRepository libroRepository;

    // Añadir libro a la biblioteca
    @PostMapping("/guardar")
    public Biblioteca guardarRegistro(@RequestParam Long usuarioId,
                                      @RequestParam Long libroId,
                                      @RequestParam boolean esLectura,
                                      @RequestParam boolean esFavorito,
                                      @RequestParam(required = false) Map<Integer, List<Subrayado>> anotaciones) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        Libro libro = libroRepository.findById(libroId).orElse(null);

        if (usuario != null && libro != null) {
            return bibliotecaService.guardarRegistro(usuario, libro, esLectura, esFavorito, anotaciones);
        }

        return null; // si no existe el usuario o el libro
    }

    @GetMapping("/lecturas")
    public List<Libro> obtenerLecturas(@RequestParam  Long usuarioId) {
        return bibliotecaService.obtenerLecturas(usuarioId);
    }

    @GetMapping("/favoritos")
    public List<Libro> obtenerFavoritos(@RequestParam  Long usuarioId) {
        return bibliotecaService.obtenerFavoritos(usuarioId);
    }

    @PutMapping("/anotaciones")
    public ResponseEntity<Void> actualizarAnotaciones(@RequestBody Anotacion anotacion) {
        try {
            bibliotecaService.actualizarAnotaciones(anotacion.getIdBiblioteca(), anotacion.getAnotaciones());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/anotaciones/{idBiblioteca}")
    public ResponseEntity<Map<Integer, List<Subrayado>>> obtenerAnotaciones(@PathVariable Long idBiblioteca) {
        try {
            Map<Integer, List<Subrayado>> anotaciones = bibliotecaService.obtenerAnotaciones(idBiblioteca);
            return ResponseEntity.ok(anotaciones);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



}
