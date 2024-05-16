package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.Peluqueria;
import com.tallerwebi.dominio.Veterinaria;

import java.util.List;

public interface ServicioMapaMascotero {
    List<Mascota> getMascotas();

    List<Veterinaria> getVeterinarias();

    List<Peluqueria> getPeluquerias();

    List<Veterinaria> buscarVeterinariasPorZona(String zona);

    List<Peluqueria> buscarPeluqueriasPorZona(String zona);

    List<Mascota> buscarMascotasPorZona(String zona);
}
