package com.LiterAlura_Challenge.Challenge_LiterAlura.modelos;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String nombreAutor;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> idiomas;

    private double descargas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;


    public Libro(DatosLibro datosLibros, Autor autor) {
        this.titulo = datosLibros.titulo();
        this.nombreAutor = datosLibros.autor().stream().map(DatosAutor::nombre).collect(Collectors.toList()).toString();
    this.descargas = datosLibros.totalDescargas();
    this.idiomas = datosLibros.idiomas();
    this.autor = autor;
    }

    @Override
    public String toString() {
        return "Datos del Libro:\n"+
                "Titulo: "+ titulo + '\n' +
                "Autor: " + nombreAutor + '\n' +
                "Descargas: " + descargas +'\n' +
                "Idiomas: " + idiomas + '\n';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public Autor getAutor() {

        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
        if (autor != null && !autor.getLibros().contains(this)) {
            autor.getLibros().add(this);
        }
    }

    public List<String> getIdiomas() {
        return idiomas;
    }


    public void setIdiomas(List<String> idiomas) {
            this.idiomas = idiomas;
        }

    public double getDescargas() {
        return descargas;
    }

    public void setDescargas(double descargas) {
        this.descargas = descargas;
    }


}

