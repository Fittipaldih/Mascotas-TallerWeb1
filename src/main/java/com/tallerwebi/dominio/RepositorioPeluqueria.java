package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioPeluqueria {
    List<Peluqueria> dameTodasLasPeluquerias();
    void guardarPeluqueria(Peluqueria peluqueria);
    void modificarPeluqueria(Peluqueria peluqueria);
    List<Peluqueria> buscarPeluqueriasPorZona(String zona);
}

