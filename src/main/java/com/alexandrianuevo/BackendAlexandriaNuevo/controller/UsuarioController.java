package com.alexandrianuevo.BackendAlexandriaNuevo.controller;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Usuario;
import com.alexandrianuevo.BackendAlexandriaNuevo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean registrar(@RequestParam String primerNombre,
                             @RequestParam String segundoNombre,
                             @RequestParam String email,
                             @RequestParam String contrasena,
                             @RequestParam String role) {
        return usuarioService.registrarUsuario(primerNombre, segundoNombre, email, contrasena, role);
    }
}
