package com.alexandrianuevo.BackendAlexandriaNuevo.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Entity
@Table(name = "bibliotecas")
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "libro_id")
    private Long libroId;

    @Column(name = "es_favorito")
    private boolean esFavorito;

    @Column(name = "en_lectura")
    private boolean enLectura;

    @Type(JsonBinaryType.class)
    @Column(name = "anotaciones", columnDefinition = "jsonb")
    private Map<Integer, List<Anotacion>> anotaciones;

    @Column(name = "fecha")
    private LocalDateTime fecha;


    // Getters y setters
    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public boolean isEsFavorito() {
        return esFavorito;
    }

    public void setEsFavorito(boolean esFavorito) {
        this.esFavorito = esFavorito;
    }

    public boolean isEnLectura() {
        return enLectura;
    }

    public void setEnLectura(boolean enLectura) {
        this.enLectura = enLectura;
    }

    public Map<Integer, List<Anotacion>> getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(Map<Integer, List<Anotacion>> anotaciones) {
        this.anotaciones = anotaciones;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

}

