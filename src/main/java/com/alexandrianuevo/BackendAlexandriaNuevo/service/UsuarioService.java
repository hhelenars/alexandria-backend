package com.alexandrianuevo.BackendAlexandriaNuevo.service;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Usuario;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Comprobar si el usuario existe con email y contraseña
    public Usuario validarCredenciales(String email, String contrasena) {
        System.out.println("email: "+email+"   contrasena: "+contrasena);
        return usuarioRepository.findByEmailAndContrasena(email, contrasena).orElse(null);
    }

    public boolean validarEmail(String email) {
        System.out.println("email: "+email);
        return usuarioRepository.findByEmail(email).isPresent();
    }

    public boolean registrarUsuario(String primerNombre, String segundoNombre, String email, String contrasena, String role) {
        boolean usuarioexiste = validarEmail(email);
        if (!usuarioexiste) {
            Usuario nuevo = new Usuario(primerNombre, segundoNombre, email, contrasena, role);
            usuarioRepository.save(nuevo);
            return true;
        }
        return false; // ya existe un usuario con ese email
    }
}
