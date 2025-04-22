package com.alexandrianuevo.BackendAlexandriaNuevo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "primer_nombre")
    private String primerNombre;

    @Column(name = "segundo_nombre")
    private String segundoNombre;

    private String email;
    private String contrasena;
    private String role;

    // Constructor vacío
    public Usuario() {}

    public Usuario(String primerNombre, String segundoNombre, String email, String contrasena, String role) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.email = email;
        this.contrasena = contrasena;
        this.role = role;
    }

    // Getters y setters

    public Long getId() { return id; }

    public String getPrimerNombre() { return primerNombre; }
    public void setPrimerNombre(String primerNombre) { this.primerNombre = primerNombre; }

    public String getSegundoNombre() { return segundoNombre; }
    public void setSegundoNombre(String segundoNombre) { this.segundoNombre = segundoNombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

