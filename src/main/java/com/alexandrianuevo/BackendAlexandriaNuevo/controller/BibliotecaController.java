package com.alexandrianuevo.BackendAlexandriaNuevo.controller;

import com.alexandrianuevo.BackendAlexandriaNuevo.anotaciones.Anotacion;
import com.alexandrianuevo.BackendAlexandriaNuevo.anotaciones.Subrayado;
import com.alexandrianuevo.BackendAlexandriaNuevo.model.Biblioteca;
import com.alexandrianuevo.BackendAlexandriaNuevo.model.Libro;
import com.alexandrianuevo.BackendAlexandriaNuevo.model.Usuario;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.LibroRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.UsuarioRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.response.LibroResponse;
import com.alexandrianuevo.BackendAlexandriaNuevo.service.BibliotecaService;
import com.alexandrianuevo.BackendAlexandriaNuevo.util.JwtUtil;
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







}
