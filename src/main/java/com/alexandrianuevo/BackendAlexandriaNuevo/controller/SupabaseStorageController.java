package com.alexandrianuevo.BackendAlexandriaNuevo.controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.alexandrianuevo.BackendAlexandriaNuevo.service.SupabaseStorageService;

@RestController
@RequestMapping("/api/epubs")
public class SupabaseStorageController {

    private final SupabaseStorageService supabaseStorageService;

    public SupabaseStorageController() {
        this.supabaseStorageService = new SupabaseStorageService();
    }

    @GetMapping("/{nombreArchivo}")
    public ResponseEntity<String> obtenerURLFirmada(@PathVariable String nombreArchivo) {
        String urlFirmada = supabaseStorageService.obtenerURLFirmada(nombreArchivo);

        if (urlFirmada != null) {
            return ResponseEntity.ok(urlFirmada);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener URL");
        }
    }
}

