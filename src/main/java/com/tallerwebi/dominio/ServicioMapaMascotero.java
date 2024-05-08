package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioMapaMascotero {
    List<Mascota> getMascotas();

    List<Veterinaria> getVeterinarias();

    List<Peluqueria> getPeluquerias();

    List<Veterinaria> buscarVeterinariasPorZona(String zona);

    List<Peluqueria> buscarPeluqueriasPorZona(String zona);

    List<Mascota> buscarMascotasPorZona(String zona);
}
