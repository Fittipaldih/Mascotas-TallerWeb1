package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioVeterinaria {
        List<Veterinaria> dameTodasLasVeterinarias();
        List<Veterinaria> buscarVeterinariasPorZona(String zona);
        void guardarVeterinaria(Veterinaria veterinaria);
        void modificarTelefonoVeterinaria(Veterinaria veterinaria);
        void eliminarVeterinaria(Veterinaria veterinaria);
}
