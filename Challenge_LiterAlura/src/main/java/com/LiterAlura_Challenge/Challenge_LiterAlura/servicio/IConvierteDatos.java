package com.LiterAlura_Challenge.Challenge_LiterAlura.servicio;

public interface IConvierteDatos {
    <T> T obtenerDatos (String json, Class<T> clase);
}
