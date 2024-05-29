package com.tallerwebi.dominio.servicios.interfaces;

import com.tallerwebi.dominio.PublicacionDonacion;
import com.tallerwebi.dominio.excepcion.DonacionException;
import org.springframework.web.multipart.MultipartFile;

public interface ServicioPublicarDonacion {

    void publicarDonacion(PublicacionDonacion donacion, MultipartFile imagen) throws DonacionException;
}
