package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.PublicacionPerdido;
import com.tallerwebi.dominio.excepcion.HistoriaException;
import com.tallerwebi.dominio.excepcion.PerdidoException;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPublicarHistoriaImp implements ServicioPublicarHistoria {
    @Autowired
    private RepositorioPublicacionImpl repositorioPublicarPerdido;

    @Override
    public void publicarHistoria(PublicacionHistoria historia) throws HistoriaException {
        repositorioPublicarPerdido.guardarHistoria(historia);
    }
}
