package com.LiterAlura_Challenge.Challenge_LiterAlura.repositorio;

import com.LiterAlura_Challenge.Challenge_LiterAlura.modelos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :año AND a.fechaDeMuerte >= :año")
    List<Autor> autoresVivosEnDeterminadoAño(int año);
}
