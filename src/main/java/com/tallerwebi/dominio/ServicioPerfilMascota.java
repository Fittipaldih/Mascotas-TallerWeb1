package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.MascotaNoEncontrada;

public interface ServicioPerfilMascota {
    Mascota buscarMascotaPorIdPublicacion(Long idPublicacion) throws MascotaNoEncontrada;
}
