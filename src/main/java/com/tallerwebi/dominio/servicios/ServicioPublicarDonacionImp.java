package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.PublicacionDonacion;
import com.tallerwebi.dominio.excepcion.DonacionException;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPublicarDonacionImp implements ServicioPublicarDonacion {
    @Autowired
    private RepositorioPublicacionImpl repositorioPublicarDonacion;

    @Override
    public void publicarDonacion(PublicacionDonacion donacion) throws DonacionException {
        this.repositorioPublicarDonacion.guardarDonacion(donacion);
    }
}
