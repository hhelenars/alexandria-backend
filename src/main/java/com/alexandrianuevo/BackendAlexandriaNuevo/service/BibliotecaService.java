package com.alexandrianuevo.BackendAlexandriaNuevo.service;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Anotacion;
import com.alexandrianuevo.BackendAlexandriaNuevo.model.Biblioteca;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.BibliotecaRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.LibroRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.response.LibroResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Autowired
    private LibroRepository libroRepository;



    // FAVORITOS
    public List<LibroResponse> obtenerFavoritos(Long usuarioId) {
        List<Biblioteca> registros = bibliotecaRepository.findByUsuarioIdAndEsFavorito(usuarioId, true);
        List<LibroResponse> favoritos = new ArrayList<>();

        for (Biblioteca registro : registros) {
            libroRepository.findById(registro.getLibroId()).ifPresent(libro -> {
                favoritos.add(new LibroResponse(libro.getId(), libro.getTitulo(), libro.getAutor(), libro.getCategoria()));
            });
        }
        return favoritos;
    }

    // LECTURAS
    public List<LibroResponse> obtenerLecturas(Long usuarioId) {
        List<Biblioteca> registros = bibliotecaRepository.findByUsuarioIdAndEnLectura(usuarioId, true);
        List<LibroResponse> lecturas = new ArrayList<>();


        for (Biblioteca registro : registros) {
            libroRepository.findById(registro.getLibroId()).ifPresent(libro -> {
                lecturas.add(new LibroResponse(libro.getId(), libro.getTitulo(), libro.getAutor(),  libro.getCategoria()));
            });
            System.out.println(registro.getId());
        }
        if (lecturas.isEmpty())
            System.out.println("No hay nada en lecturas");

        return lecturas;
    }

    public void registrarLectura(Long usuarioId, Long libroId) {
        Biblioteca registroExistente = bibliotecaRepository.findByUsuarioId(usuarioId).stream()
                .filter(b -> b.getLibroId().equals(libroId))
                .findFirst()
                .orElse(null);

        if (registroExistente != null) {
            if (!registroExistente.isEnLectura()) {
                registroExistente.setEnLectura(true);
                bibliotecaRepository.save(registroExistente);
                System.out.println(" Se actualizó enLectura=true para el libroId=" + libroId);
            } else {
                System.out.println(" Ya estaba en lectura: libroId=" + libroId);
            }
            return;
        }

        // Si no existe, se crea una nueva entrada
        Biblioteca nueva = new Biblioteca();
        nueva.setUsuarioId(usuarioId);
        nueva.setLibroId(libroId);
        nueva.setEnLectura(true);
        nueva.setEsFavorito(false);
        nueva.setFecha(LocalDateTime.now());

        bibliotecaRepository.save(nueva);
        System.out.println(" Nueva lectura registrada: usuario=" + usuarioId + ", libro=" + libroId);
    }

    public void eliminarLectura(Long usuarioId, Long libroId) {
        Biblioteca registroExistente = bibliotecaRepository.findByUsuarioId(usuarioId).stream()
                .filter(b -> b.getLibroId().equals(libroId))
                .findFirst()
                .orElse(null);

        if (registroExistente != null) {
            if (registroExistente.isEnLectura()) {
                registroExistente.setEnLectura(false);
                bibliotecaRepository.save(registroExistente);
            } else {
                System.out.println(" Ya estaba en favoritos: libroId=" + libroId);
            }
            return;
        }
    }


    public void registrarFavoritos(Long usuarioId, Long libroId) {
        Biblioteca registroExistente = bibliotecaRepository.findByUsuarioId(usuarioId).stream()
                .filter(b -> b.getLibroId().equals(libroId))
                .findFirst()
                .orElse(null);

        if (registroExistente != null) {
            if (!registroExistente.isEsFavorito()) {
                registroExistente.setEsFavorito(true);
                bibliotecaRepository.save(registroExistente);
            } else {
                System.out.println(" Ya estaba en favoritos: libroId=" + libroId);
            }
            return;
        }
    }

    public void eliminarFavoritos(Long usuarioId, Long libroId) {
        Biblioteca registroExistente = bibliotecaRepository.findByUsuarioId(usuarioId).stream()
                .filter(b -> b.getLibroId().equals(libroId))
                .findFirst()
                .orElse(null);

        if (registroExistente != null) {
            if (registroExistente.isEsFavorito()) {
                registroExistente.setEsFavorito(false);
                bibliotecaRepository.save(registroExistente);
            } else {
                System.out.println(" Ya estaba en favoritos: libroId=" + libroId);
            }
            return;
        }
    }



    public void guardarAnotaciones(Long usuarioId, Long libroId, Map<Integer, List<Anotacion>> nuevasAnotaciones) {
        bibliotecaRepository.findByUsuarioId(usuarioId).stream()
                .filter(b -> b.getLibroId().equals(libroId))
                .findFirst()
                .ifPresent(b -> {
                    b.setAnotaciones(nuevasAnotaciones);
                    bibliotecaRepository.save(b);
                });
    }

    public Map<Integer, List<Anotacion>> obtenerAnotaciones(Long usuarioId, Long libroId) {
        return bibliotecaRepository.findByUsuarioId(usuarioId).stream()
                .filter(b -> b.getLibroId().equals(libroId))
                .findFirst()
                .map(Biblioteca::getAnotaciones)
                .orElse(null);
    }
}

