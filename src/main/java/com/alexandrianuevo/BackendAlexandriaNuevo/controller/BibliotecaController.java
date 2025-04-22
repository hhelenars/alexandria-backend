package com.alexandrianuevo.BackendAlexandriaNuevo.controller;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Biblioteca;
import com.alexandrianuevo.BackendAlexandriaNuevo.model.Libro;
import com.alexandrianuevo.BackendAlexandriaNuevo.model.Usuario;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.LibroRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.UsuarioRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                                      @RequestParam(required = false) String anotaciones) {
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

}
