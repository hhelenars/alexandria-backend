package com.alexandrianuevo.BackendAlexandriaNuevo.controller;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Usuario;
import com.alexandrianuevo.BackendAlexandriaNuevo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public Usuario login(@RequestParam String email, @RequestParam String contrasena) {
        return usuarioService.validarCredenciales(email, contrasena);
    }

    @PostMapping("/registrar")
    public Usuario registrar(@RequestBody Usuario usuario) {
        boolean exito = usuarioService.registrarUsuario(
                usuario.getPrimerNombre(),
                usuario.getSegundoNombre(),
                usuario.getEmail(),
                usuario.getContrasena(),
                usuario.getRole()
        );

        return exito ? usuario : null;
    }
}
