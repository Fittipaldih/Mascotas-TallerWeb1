package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;

import java.util.List;

public class ServicioMapaMascoteroImpl implements ServicioMapaMascotero {

    private RepositorioMascota repositorioMascota;
    private RepositorioVeterinaria repositorioVeterinaria;
    private RepositorioPeluqueria repositorioPeluqueria;

    public ServicioMapaMascoteroImpl(RepositorioMascota repositorioMascota, RepositorioVeterinaria repositorioVeterinaria, RepositorioPeluqueria repositorioPeluqueria) {
        this.repositorioMascota = repositorioMascota;
        this.repositorioVeterinaria = repositorioVeterinaria;
        this.repositorioPeluqueria = repositorioPeluqueria;

    }

    @Override
    public List<Veterinaria> buscarVeterinariasPorZona(String zona) {
        return repositorioVeterinaria.buscarVeterinariasPorZona(zona);
    }

    @Override
    public List<Peluqueria> buscarPeluqueriasPorZona(String zona) {
        return repositorioPeluqueria.buscarPeluqueriasPorZona(zona);
    }

    @Override
    public List<Mascota> buscarMascotasPorZona(String zona) {
        return repositorioMascota.buscarMascotasPorZona(zona);
    }
}
