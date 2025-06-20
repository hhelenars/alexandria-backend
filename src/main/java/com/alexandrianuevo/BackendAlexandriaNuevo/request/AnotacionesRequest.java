package com.alexandrianuevo.BackendAlexandriaNuevo.request;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Anotacion;

import java.util.List;
import java.util.Map;

public class AnotacionesRequest {

    private Long libroId;
    private Map<Integer, List<Anotacion>> anotaciones;

    // Getters y setters
    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public Map<Integer, List<Anotacion>> getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(Map<Integer, List<Anotacion>> anotaciones) {
        this.anotaciones = anotaciones;
    }
}
