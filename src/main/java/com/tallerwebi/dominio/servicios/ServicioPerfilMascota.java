package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.excepcion.MascotaNoEncontrada;

public interface ServicioPerfilMascota {
    Mascota buscarMascotaPorIdPublicacion(Long idPublicacion) throws MascotaNoEncontrada;
}
