package com.alexandrianuevo.BackendAlexandriaNuevo.service;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Libro;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.LibroRepository;
import com.alexandrianuevo.BackendAlexandriaNuevo.util.LibroComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> buscarPorTituloOAutor(String texto) {
        List<Libro> libros = libroRepository.findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(texto, texto);
        libros.sort(new LibroComparator());
        return libros;
    }

    public List<Libro> obtenerTodos() {
        List<Libro> libros = libroRepository.findAll();
        libros.sort(new LibroComparator());
        return libros;
    }

    public byte[] descargarArchivoEPUB(String ruta) throws Exception {
        String supabaseUrl = "https://rrkjpjcyrofglmbllrqs.supabase.co";
        String apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJya2pwamN5cm9mZ2xtYmxscnFzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDE0Njg4MzcsImV4cCI6MjA1NzA0NDgzN30.By_gbv2tSjlKQ7JOS6rEJLKbvbtaizxxpsvCJpuxenI";
        String bucket = "epubs";
        String url = supabaseUrl + "/storage/v1/object/" + bucket + "/" + ruta;

        System.out.println("🛰️ URL de descarga: " + url); // debug

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("apikey", apiKey)
                .header("Authorization", "Bearer " + apiKey)
                .build();

        HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

        if (response.statusCode() == 404) {
            throw new FileNotFoundException("Archivo no encontrado en Supabase");
        }

        return response.body();
    }


}

