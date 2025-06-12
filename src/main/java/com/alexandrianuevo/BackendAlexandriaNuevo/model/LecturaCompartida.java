package com.alexandrianuevo.BackendAlexandriaNuevo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;


@Entity
@Table(name = "lecturas_compartidas")
public class LecturaCompartida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "usuario_destino_id", nullable = false)
    private Long usuarioDestinoId;

    @Column(name = "libro_id", nullable = false)
    private Long libroId;

    @Column(name = "fecha_compartido")
    private LocalDateTime fechaCompartido;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb") // Para Postgres, si usas MySQL pon "json"
    private Map<Integer, List<Anotacion>> anotaciones = new HashMap<>();

    // ... getters y setters ...
    public Map<Integer, List<Anotacion>> getAnotaciones() {
        return anotaciones;
    }
    public void setAnotaciones(Map<Integer, List<Anotacion>> anotaciones) {
        this.anotaciones = anotaciones;
    }




    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getUsuarioDestinoId() { return usuarioDestinoId; }
    public void setUsuarioDestinoId(Long usuarioDestinoId) { this.usuarioDestinoId = usuarioDestinoId; }

    public Long getLibroId() { return libroId; }
    public void setLibroId(Long libroId) { this.libroId = libroId; }

    public LocalDateTime getFechaCompartido() { return fechaCompartido; }
    public void setFechaCompartido(LocalDateTime fechaCompartido) { this.fechaCompartido = fechaCompartido; }
}