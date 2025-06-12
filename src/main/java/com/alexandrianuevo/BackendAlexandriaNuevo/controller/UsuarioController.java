package com.alexandrianuevo.BackendAlexandriaNuevo.controller;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Usuario;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.UsuarioRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.response.LoginResponse;
import com.alexandrianuevo.BackendAlexandriaNuevo.service.UsuarioService;
import com.alexandrianuevo.BackendAlexandriaNuevo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String contrasena) {
        Usuario usuario =  usuarioService.validarCredenciales(email, contrasena);
        if (usuario != null) {
            String token = jwtUtil.generarToken(usuario.getId(), usuario.getPrimerNombre(), usuario.getSegundoNombre());
            LoginResponse response = new LoginResponse(token, usuario.getPrimerNombre(), usuario.getSegundoNombre());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestParam String primerNombre,
                                            @RequestParam String segundoNombre,
                                            @RequestParam String email,
                                            @RequestParam String contrasena,
                                            @RequestParam String role) {

        boolean exito = usuarioService.registrarUsuario(primerNombre, segundoNombre, email, contrasena, role);


        return exito ? ResponseEntity.ok("Usuario registrado correctamente") : ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un usuario con ese email");
    }

    @GetMapping("/lista")
    public List<Usuario> listarUsuarios(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Long usuarioActualId = jwtUtil.extraerIdUsuario(token);
        // Devuelve todos los usuarios menos el actual
        return usuarioRepository.findAll()
                .stream()
                .filter(u -> !u.getId().equals(usuarioActualId))
                .toList();
    }
}
