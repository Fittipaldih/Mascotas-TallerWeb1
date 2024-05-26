package com.tallerwebi.dominio.repositorioInterfaces;

public interface RepositorioComentario {
    void guardarNuevoComentarioEnPublicacion(String contenido, Long idPublicacion) throws Exception;
}
