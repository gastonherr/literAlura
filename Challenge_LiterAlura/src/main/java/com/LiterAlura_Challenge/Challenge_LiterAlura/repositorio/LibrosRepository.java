package com.LiterAlura_Challenge.Challenge_LiterAlura.repositorio;

import com.LiterAlura_Challenge.Challenge_LiterAlura.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibrosRepository extends JpaRepository<Libro, Long> {
    Libro findByTituloContainsIgnoreCase(String nombreLibro);

    List<Libro> findByIdiomas(String idioma);
}
