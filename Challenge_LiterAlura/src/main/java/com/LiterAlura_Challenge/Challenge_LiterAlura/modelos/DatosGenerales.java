package com.LiterAlura_Challenge.Challenge_LiterAlura.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosGenerales(
        @JsonAlias("results") List<DatosLibro> resultado) {
}
