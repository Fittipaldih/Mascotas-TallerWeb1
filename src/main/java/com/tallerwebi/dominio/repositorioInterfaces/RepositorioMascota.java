package com.tallerwebi.dominio.repositorioInterfaces;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.Zona;

import java.util.List;

public interface RepositorioMascota {

    List<Mascota> dameTodasLasMascotas();

    List<Mascota> buscarMascotasPorZona(Zona zona);

    Mascota buscarMascotaPorId(Long idMascota);

    void guardarMascota(Mascota mascota);

    void modificarMascota(Mascota mascota);

    void eliminarMascota(Mascota mascota);

    //Mascota buscarMascotaPorIdPublicacion(Long idPublicacion);

    Boolean verificarSiMascotaExiste(Mascota mascota);
}
