package com.alexandrianuevo.BackendAlexandriaNuevo.repository;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {

    // Obtener todos los libros que un usuario ha marcado como lectura
    List<Biblioteca> findByUsuarioIdAndEnLectura(Long usuarioId, boolean enLectura);

    // Obtener todos los libros que un usuario ha marcado como favorito
    List<Biblioteca> findByUsuarioIdAndEsFavorito(Long usuarioId, boolean esFavorito);

    // Obtener toda la biblioteca de un usuario
    List<Biblioteca> findByUsuarioId(Long usuarioId);

}
