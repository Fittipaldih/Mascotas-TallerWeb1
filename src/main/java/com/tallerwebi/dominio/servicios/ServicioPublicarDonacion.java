package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.PublicacionDonacion;
import com.tallerwebi.dominio.excepcion.DonacionException;

public interface ServicioPublicarDonacion {

    void publicarDonacion(PublicacionDonacion donacion) throws DonacionException;
}
