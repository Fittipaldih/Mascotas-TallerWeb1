package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.PublicacionPerdido;
import com.tallerwebi.dominio.excepcion.PerdidoException;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ServicioPublicarPerdidoImp implements ServicioPublicarPerdido {

    @Autowired
    private RepositorioPublicacionImpl repositorioPublicarPerdido;

    @Override
    public void publicarPerdido(PublicacionPerdido perdido) throws PerdidoException {
             repositorioPublicarPerdido.guardarPerdido(perdido);
    }
}
