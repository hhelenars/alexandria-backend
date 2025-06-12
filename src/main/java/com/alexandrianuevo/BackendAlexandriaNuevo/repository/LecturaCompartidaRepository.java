package com.alexandrianuevo.BackendAlexandriaNuevo.repository;


import com.alexandrianuevo.BackendAlexandriaNuevo.model.LecturaCompartida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LecturaCompartidaRepository extends JpaRepository<LecturaCompartida, Long> {
    // Buscar lecturas compartidas entre dos usuarios
    List<LecturaCompartida> findByUsuarioIdAndUsuarioDestinoId(Long usuarioId, Long usuarioDestinoId);

    // Buscar todos los libros compartidos con un usuario concreto
    List<LecturaCompartida> findByUsuarioDestinoId(Long usuarioDestinoId);

    // Buscar todos los usuarios a los que yo les he compartido algún libro
    List<LecturaCompartida> findByUsuarioId(Long usuarioId);

    @Query("SELECT lc FROM LecturaCompartida lc " +
            "WHERE (lc.usuarioId = :usuarioId AND lc.usuarioDestinoId = :usuarioDestinoId) " +
            "   OR (lc.usuarioId = :usuarioDestinoId AND lc.usuarioDestinoId = :usuarioId)")
    List<LecturaCompartida> findByUsuarios(@Param("usuarioId") Long usuarioId, @Param("usuarioDestinoId") Long usuarioDestinoId);

    // En LecturaCompartidaRepository
    @Query("SELECT lc FROM LecturaCompartida lc WHERE " +
            "(lc.usuarioId = :id1 AND lc.usuarioDestinoId = :id2) " +
            "OR (lc.usuarioId = :id2 AND lc.usuarioDestinoId = :id1)")
    List<LecturaCompartida> findEntreAmbosUsuarios(Long id1, Long id2);


    // Busca la lectura compartida entre dos usuarios y ese libro (en cualquier orden)
    @Query("SELECT lc FROM LecturaCompartida lc WHERE " +
            "((lc.usuarioId = :u1 AND lc.usuarioDestinoId = :u2) OR (lc.usuarioId = :u2 AND lc.usuarioDestinoId = :u1)) " +
            "AND lc.libroId = :libroId")
    LecturaCompartida findByUsuariosAndLibro(@Param("u1") Long usuarioId1,
                                             @Param("u2") Long usuarioId2,
                                             @Param("libroId") Long libroId);

}