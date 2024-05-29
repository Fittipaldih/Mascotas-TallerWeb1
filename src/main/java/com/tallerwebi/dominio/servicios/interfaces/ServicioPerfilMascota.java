package com.tallerwebi.dominio.servicios.interfaces;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.excepcion.MascotaNoEncontrada;

public interface ServicioPerfilMascota {
    Mascota buscarMascotaPorId(Long id) throws MascotaNoEncontrada;
}
