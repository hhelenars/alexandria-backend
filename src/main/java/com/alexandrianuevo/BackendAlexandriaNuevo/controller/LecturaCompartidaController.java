package com.alexandrianuevo.BackendAlexandriaNuevo.controller;


import com.alexandrianuevo.BackendAlexandriaNuevo.model.LecturaCompartida;
import com.alexandrianuevo.BackendAlexandriaNuevo.model.Libro;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.LecturaCompartidaRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.LibroRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.response.LibroResponse;
import com.alexandrianuevo.BackendAlexandriaNuevo.service.LecturaCompartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lecturas-compartidas")
public class LecturaCompartidaController {

    @Autowired
    private LecturaCompartidaService lecturaCompartidaService;

    @Autowired
    private LecturaCompartidaRepository lecturaCompartidaRepository;

    @Autowired
    private LibroRepository libroRepository;

    // Compartir un libro con otro usuario
    @PostMapping("/compartir")
    public LecturaCompartida compartirLectura(
            @RequestParam Long usuarioId,
            @RequestParam Long usuarioDestinoId,
            @RequestParam Long libroId
    ) {
        return lecturaCompartidaService.compartirLectura(usuarioId, usuarioDestinoId, libroId);
    }

    // Ver usuarios a los que he compartido libros
    @GetMapping("/enviadas/{usuarioId}")
    public List<LecturaCompartida> verUsuariosCompartidosPorMi(@PathVariable Long usuarioId) {
        return lecturaCompartidaService.listarUsuariosCompartidosPorMi(usuarioId);
    }

    // Ver los libros que me han compartido
    @GetMapping("/recibidas/{usuarioDestinoId}")
    public List<LecturaCompartida> verLecturasRecibidas(@PathVariable Long usuarioDestinoId) {
        return lecturaCompartidaService.listarLecturasCompartidasConmigo(usuarioDestinoId);
    }

    // Ver lecturas compartidas entre dos usuarios concretos (opcional)
    @GetMapping("/entre-usuarios")
    public List<LecturaCompartida> lecturasEntreUsuarios(
            @RequestParam Long usuarioId,
            @RequestParam Long usuarioDestinoId
    ) {
        return lecturaCompartidaService.listarLecturasEntreUsuarios(usuarioId, usuarioDestinoId);
    }

    @GetMapping("/entre")
    public List<LibroResponse> obtenerLecturasCompartidasEntre(
            @RequestParam Long usuarioId,
            @RequestParam Long usuarioDestinoId
    ) {
        // Paso 1: busca las lecturas compartidas entre esos dos usuarios
        List<LecturaCompartida> compartidas = lecturaCompartidaRepository.findEntreAmbosUsuarios(usuarioId, usuarioDestinoId);

        // Paso 2: por cada lectura, busca el libro y construye el DTO
        List<LibroResponse> respuesta = new ArrayList<>();
        for (LecturaCompartida compartida : compartidas) {
            Libro libro = libroRepository.findById(compartida.getLibroId()).orElse(null);
            if (libro != null) {
                respuesta.add(new LibroResponse(libro.getId(), libro.getTitulo(), libro.getAutor()));
            }
        }
        return respuesta;
    }
}
