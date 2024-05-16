package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.Peluqueria;
import com.tallerwebi.dominio.Veterinaria;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioMascota;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPeluqueria;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioVeterinaria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public List<Mascota> getMascotas() {
        return repositorioMascota.dameTodasLasMascotas();
    }

    @Override
    public List<Veterinaria> getVeterinarias() {
        return repositorioVeterinaria.dameTodasLasVeterinarias();
    }

    @Override
    public List<Peluqueria> getPeluquerias() {
        return repositorioPeluqueria.dameTodasLasPeluquerias();
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