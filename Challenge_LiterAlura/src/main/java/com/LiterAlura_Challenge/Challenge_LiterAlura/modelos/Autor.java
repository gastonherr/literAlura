package com.LiterAlura_Challenge.Challenge_LiterAlura.modelos;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table (name = "autor")
public class Autor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(unique = true)
    private String nombre;

    private String fechaDeNacimiento;

    private String fechaDeMuerte;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Libro> libros = new HashSet<>();

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
        this.fechaDeMuerte = datosAutor.fechaDeMuerte();
    }

    @Override
    public String toString() {
        return
                        "Autor: " + nombre + '\n' +
                        "Fecha de Nacimiento: " + fechaDeNacimiento + '\n' +
                        "Fecha de Muerte: " + fechaDeMuerte + '\n' +
                        "Libros: " + (libros != null ?libros.stream()
                        .map(Libro::getTitulo)
                        .collect(Collectors.joining(", ")) : "N/A") +'\n' +
                        '\n';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(String fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
        for (Libro libro : libros) {
            libro.setAutor(this);
        }
    }
}


