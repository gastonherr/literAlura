package com.LiterAlura_Challenge.Challenge_LiterAlura.principal;

import com.LiterAlura_Challenge.Challenge_LiterAlura.modelos.*;
import com.LiterAlura_Challenge.Challenge_LiterAlura.repositorio.AutorRepository;
import com.LiterAlura_Challenge.Challenge_LiterAlura.repositorio.LibrosRepository;
import com.LiterAlura_Challenge.Challenge_LiterAlura.servicio.ConsumoApi;
import com.LiterAlura_Challenge.Challenge_LiterAlura.servicio.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private List<DatosLibro> datosLibros = new ArrayList<>();
    private LibrosRepository repository;
    private AutorRepository repositoryAutor;
    private List<Libro> libros;
    private List<Autor> autor;

    public Principal (){}
    public Principal(LibrosRepository repository, AutorRepository repositoryAutor) {
        this.repository = repository;
        this.repositoryAutor = repositoryAutor;
    }

    public void opcionesMenu(){
        var opcion = -1;
        while (opcion != 0) {

            muestraMenu();

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    buscarAutorVivoEnAño();
                    break;
                case 5:
                    buscarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción invalida");
            }

}
        System.out.println("****************************************");

    }

    public void buscarLibro(){
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var buscaLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + buscaLibro.replace(" ", "+"));
        var buscador = convierteDatos.obtenerDatos(json, DatosGenerales.class);

        Optional <DatosLibro> libroBuscado = buscador.resultado().stream()
                .filter(l-> l.titulo().toUpperCase().contains(buscaLibro.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            DatosLibro datosLibros = libroBuscado.get();
            DatosAutor datosAutor = datosLibros.autor().get(0);
            Autor autor = repositoryAutor.findByNombre(datosAutor.nombre());


            if (autor == null) {

                autor = new Autor(datosAutor);
                repositoryAutor.save(autor);
            }

            Libro libro = repository.findByTituloContainsIgnoreCase(datosLibros.titulo());


            if (libro == null) {
                System.out.println("El libro fue encontrado");
                libro = new Libro(datosLibros, autor);
                repository.save(libro);
                System.out.println(libro);
            }else {
                System.out.println("El libro ya se encuentra registrado");
            }
        } else {
            System.out.println("El libro no fue encontrado");

    }
}
    private void mostrarLibrosRegistrados(){
        libros = repository.findAll();
        libros.forEach(System.out::println);
    }

    private void mostrarAutoresRegistrados(){
        autor = repositoryAutor.findAll();
        autor.forEach(System.out::println);
    }

    private void buscarLibrosPorIdioma(){
        System.out.println("""
                        ********
        Ingrese un número según el idioma que desee
        
        1- Español
        2- Ingles
        3- Italiano
        4- Portugues
        
                       ********
        """);

        var  numero = teclado.nextInt();
        switch (numero) {
            case 1:
                buscarIdioma("es");
                break;
            case 2:
                buscarIdioma("en");
                break;
            case 3:
                buscarIdioma("it");
                break;
            case 4:
                buscarIdioma("pt");
                break;
            default:
                System.out.println("Opción inválida");
        }
}

    private void buscarIdioma(String idioma) {
        try {
            libros = repository.findByIdiomas(idioma);

            if (libros == null) {
                System.out.println("No se encuentran libros registrados");
            } else {
                libros.forEach(System.out::println);
            }
        }catch (Exception e){
            System.out.println("Ocurrió un error durante la búsqueda");
        }
    }

        private void buscarAutorVivoEnAño() {
            System.out.println("Ingrese el año de busqueda");

            var año = teclado.nextInt();
            autor = repositoryAutor.autoresVivosEnDeterminadoAño(año);
            if (autor == null) {
                System.out.println("No se encontraron autores para ese año");
            } else {
                autor.forEach(System.out::println);
            }
        }

        private void muestraMenu() {
            var menu = """
                                   *****
         
                    
                    1 - Buscar libros por titulo
                    2 - Mostrar libros registrados
                    3 - Mostrar autores registrados
                    4 - Mostrar autores vivos en un determinado año
                    5 - Mostrar libros por idiomas
        
                
                    0 - Salir
                                    *****
                    """;
            System.out.println(menu);
        }
        private void scannerSoloNumeros() {
            while (!teclado.hasNextInt()) {
                System.out.println("ingrese solo numeros");
                teclado.next();
            }
        }

        }



