package com.tallerwebi.dominio.repositorioInterfaces;

import com.tallerwebi.dominio.Peluqueria;
import com.tallerwebi.dominio.Zona;

import java.util.List;

public interface RepositorioPeluqueria {
    List<Peluqueria> dameTodasLasPeluquerias();

    List<Peluqueria> buscarPeluqueriasPorZona(Zona zona);

    void guardarPeluqueria(Peluqueria peluqueria);

    void modificarTelefonoPeluqueria(Peluqueria peluqueria);

    void eliminarPeluqueria(Peluqueria peluqueria);
}

