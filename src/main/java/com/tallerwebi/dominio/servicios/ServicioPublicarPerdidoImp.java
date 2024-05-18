package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.PublicacionPerdido;
import com.tallerwebi.dominio.excepcion.PerdidoException;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioPublicarPerdidoImp implements ServicioPublicarPerdido {

    private RepositorioPublicacionImpl repositorioPublicarPerdido;

    @Autowired
    public  ServicioPublicarPerdidoImp(RepositorioPublicacionImpl repositorioPublicarPerdido) {
            this.repositorioPublicarPerdido = repositorioPublicarPerdido;
    }


    @Override
    public void publicarPerdido(PublicacionPerdido perdido) throws PerdidoException {
             repositorioPublicarPerdido.guardarPerdido(perdido);
    }
}
