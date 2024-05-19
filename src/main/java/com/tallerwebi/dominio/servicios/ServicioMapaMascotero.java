package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.Peluqueria;
import com.tallerwebi.dominio.Veterinaria;
import com.tallerwebi.dominio.Zona;

import java.util.List;

public interface ServicioMapaMascotero {
    List<Mascota> getMascotas();

    List<Veterinaria> getVeterinarias();

    List<Peluqueria> getPeluquerias();

    List<Veterinaria> buscarVeterinariasPorZona(Zona zona);

    List<Peluqueria> buscarPeluqueriasPorZona(Zona zona);

    List<Mascota> buscarMascotasPorZona(Zona zona);
}
