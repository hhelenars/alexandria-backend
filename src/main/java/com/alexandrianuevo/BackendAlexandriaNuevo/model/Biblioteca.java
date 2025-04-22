package com.alexandrianuevo.BackendAlexandriaNuevo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    @Column(name = "anotaciones", columnDefinition = "jsonb")
    private String anotaciones;

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

    public String getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(String anotaciones) {
        this.anotaciones = anotaciones;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

}

