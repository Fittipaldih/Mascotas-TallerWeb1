package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioVeterinaria;
import com.tallerwebi.dominio.Veterinaria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioPeluqueria")
public class RepositorioPeluqueriaImpl implements RepositorioVeterinaria {

    @Override
    public List<Veterinaria> dameTodasLasVeterinarias() {
        return List.of();
    }

    @Override
    public void guardarVeterinaria(Veterinaria veterinaria) {

    }

    @Override
    public void modificarVeterinaria(Veterinaria veterinaria) {

    }

    @Override
    public List<Veterinaria> buscarVeterinariasPorZona(String zona) {
        return List.of();
    }
}
