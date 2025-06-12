package com.alexandrianuevo.BackendAlexandriaNuevo.service;


import com.alexandrianuevo.BackendAlexandriaNuevo.model.LecturaCompartida;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.LecturaCompartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class LecturaCompartidaService {

    @Autowired
    private LecturaCompartidaRepository lecturaCompartidaRepository;

    // Compartir un libro con otro usuario
    public LecturaCompartida compartirLectura(Long usuarioId, Long usuarioDestinoId, Long libroId) {
        LecturaCompartida lectura = new LecturaCompartida();
        lectura.setUsuarioId(usuarioId);
        lectura.setUsuarioDestinoId(usuarioDestinoId);
        lectura.setLibroId(libroId);
        lectura.setFechaCompartido(LocalDateTime.now());
        return lecturaCompartidaRepository.save(lectura);
    }

    // Listar todos los usuarios a los que he compartido libros
    public List<LecturaCompartida> listarUsuariosCompartidosPorMi(Long usuarioId) {
        return lecturaCompartidaRepository.findByUsuarioId(usuarioId);
    }

    // Listar todos los libros que me han compartido
    public List<LecturaCompartida> listarLecturasCompartidasConmigo(Long usuarioDestinoId) {
        return lecturaCompartidaRepository.findByUsuarioDestinoId(usuarioDestinoId);
    }

    // Listar lecturas compartidas entre dos usuarios
    public List<LecturaCompartida> listarLecturasEntreUsuarios(Long usuarioId, Long usuarioDestinoId) {
        return lecturaCompartidaRepository.findByUsuarioIdAndUsuarioDestinoId(usuarioId, usuarioDestinoId);
    }



}