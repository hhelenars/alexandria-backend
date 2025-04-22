package com.alexandrianuevo.BackendAlexandriaNuevo.repository;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario>  findByEmailAndContrasena(String email, String contrasena);
    Optional<Usuario>  findByEmail(String email);
}

