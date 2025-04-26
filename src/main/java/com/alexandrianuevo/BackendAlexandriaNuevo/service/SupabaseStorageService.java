package com.alexandrianuevo.BackendAlexandriaNuevo.service;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SupabaseStorageService {

    private final String SUPABASE_URL = "https://rrkjpjcyrofglmbllrqs.supabase.co";
    private final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJya2pwamN5cm9mZ2xtYmxscnFzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDE0Njg4MzcsImV4cCI6MjA1NzA0NDgzN30.By_gbv2tSjlKQ7JOS6rEJLKbvbtaizxxpsvCJpuxenI";
    private final String SERVICE_ROLE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJya2pwamN5cm9mZ2xtYmxscnFzIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0MTQ2ODgzNywiZXhwIjoyMDU3MDQ0ODM3fQ.dE7UgGxaSNMQSrRYm5z8iaiIOwL6cPFmlvcnDZtWxHo";

    public String obtenerURLFirmada(String nombreArchivo) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SUPABASE_URL + "/storage/v1/object/sign/epubs/" + nombreArchivo))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + SERVICE_ROLE_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"expiresIn\":3600}")) // 1 hora
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject json = new JSONObject(response.body());
                return SUPABASE_URL + "/storage/v1/" + json.getString("signedURL");
            } else {
                System.out.println("Error al firmar URL: " + response.body());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

