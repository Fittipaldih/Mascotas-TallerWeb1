package com.tallerwebi.dominio.repositorioInterfaces;

import com.tallerwebi.dominio.Veterinaria;
import com.tallerwebi.dominio.Zona;

import java.util.List;

public interface RepositorioVeterinaria {
    List<Veterinaria> dameTodasLasVeterinarias();

    List<Veterinaria> buscarVeterinariasPorZona(Zona zona);

    void guardarVeterinaria(Veterinaria veterinaria);

    void modificarTelefonoVeterinaria(Veterinaria veterinaria);

    void eliminarVeterinaria(Veterinaria veterinaria);

    Veterinaria buscarVeterinariaPorNombre(String nombre);
}
