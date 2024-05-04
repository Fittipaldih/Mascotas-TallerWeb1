package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioMapaMascotero {
    List<Veterinaria> buscarVeterinariasPorZona(String zona);
    List<Peluqueria> buscarPeluqueriasPorZona(String zona);
    List<Mascota> buscarMascotasPorZona(String zone);
}
