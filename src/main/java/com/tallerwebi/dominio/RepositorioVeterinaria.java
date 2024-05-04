package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioVeterinaria {
        List<Veterinaria> dameTodasLasVeterinarias();
        void guardarVeterinaria(Veterinaria veterinaria);
        void modificarVeterinaria(Veterinaria veterinaria);
        List<Veterinaria> buscarVeterinariasPorZona(String zona);
}
