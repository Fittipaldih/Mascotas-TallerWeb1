package com.tallerwebi.dominio.repositorioInterfaces;

import com.tallerwebi.dominio.Peluqueria;

import java.util.List;

public interface RepositorioPeluqueria {
    List<Peluqueria> dameTodasLasPeluquerias();

    List<Peluqueria> buscarPeluqueriasPorZona(String zona);

    void guardarPeluqueria(Peluqueria peluqueria);

    void modificarTelefonoPeluqueria(Peluqueria peluqueria);

    void eliminarPeluqueria(Peluqueria peluqueria);
}

