package com.alexandrianuevo.BackendAlexandriaNuevo.controller;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Anotacion;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.LibroRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.UsuarioRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.request.AnotacionesRequest;
import com.alexandrianuevo.BackendAlexandriaNuevo.response.LibroResponse;
import com.alexandrianuevo.BackendAlexandriaNuevo.service.BibliotecaService;
import com.alexandrianuevo.BackendAlexandriaNuevo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping("/lecturas")
    public  ResponseEntity<List<LibroResponse>> obtenerLecturas(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            Long usuarioId = jwtUtil.extraerIdUsuario(token);

            if (jwtUtil.estaExpirado(token)) {
                return ResponseEntity.status(401).build(); // Token caducado
            }

            List<LibroResponse> lecturas = bibliotecaService.obtenerLecturas(usuarioId);
            return ResponseEntity.ok(lecturas);

        } catch (Exception e) {
            return ResponseEntity.status(403).build(); // Token inválido
        }
    }

    @GetMapping("/favoritos")
    public  ResponseEntity<List<LibroResponse>> obtenerFavoritos(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            Long usuarioId = jwtUtil.extraerIdUsuario(token);

            if (jwtUtil.estaExpirado(token)) {
                return ResponseEntity.status(401).build(); // Token caducado
            }

            List<LibroResponse> favoritos = bibliotecaService.obtenerFavoritos(usuarioId);
            return ResponseEntity.ok(favoritos);

        } catch (Exception e) {
            return ResponseEntity.status(403).build(); // Token inválido
        }
    }

    @PostMapping("/enlectura")
    public ResponseEntity<?> registrarLectura(@RequestHeader("Authorization") String authHeader,
                                              @RequestParam Long libroId) {
        try {
            String token = authHeader.replace("Bearer ", "");
            Long usuarioId = jwtUtil.extraerIdUsuario(token);

            if (jwtUtil.estaExpirado(token)) {
                return ResponseEntity.status(401).body("Token expirado");
            }

            bibliotecaService.registrarLectura(usuarioId, libroId);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(403).body("Error al registrar lectura");
        }
    }

    @PostMapping("/guardar-anotaciones")
    public ResponseEntity<?> guardarAnotaciones(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody AnotacionesRequest request) {

        try {
            String token = authHeader.replace("Bearer ", "");
            Long usuarioId = jwtUtil.extraerIdUsuario(token);

            if (jwtUtil.estaExpirado(token)) {
                return ResponseEntity.status(401).body("Token expirado");
            }

            bibliotecaService.guardarAnotaciones(usuarioId, request.getLibroId(), request.getAnotaciones());
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al guardar anotaciones");
        }
    }


    @GetMapping("/recuperar-anotaciones")
    public ResponseEntity<?> obtenerAnotaciones(@RequestHeader("Authorization") String authHeader,
                                                @RequestParam Long libroId) {
        try {
            String token = authHeader.replace("Bearer ", "");
            Long usuarioId = jwtUtil.extraerIdUsuario(token);

            if (jwtUtil.estaExpirado(token)) {
                return ResponseEntity.status(401).body("Token expirado");
            }

            Map<Integer, List<Anotacion>> anotaciones = bibliotecaService.obtenerAnotaciones(usuarioId, libroId);

            if (anotaciones == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(anotaciones);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(403).body("Error al recuperar anotaciones");
        }
    }

}
