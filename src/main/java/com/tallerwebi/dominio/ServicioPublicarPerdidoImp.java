package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.PerdidoExeption;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPublicarPerdidoImp implements ServicioPublicarPerdido {

    private RepositorioPublicacionImpl repositorioPublicarPerdido;

    @Autowired
    public  ServicioPublicarPerdidoImp(RepositorioPublicacionImpl repositorioPublicarPerdido) {
            this.repositorioPublicarPerdido = repositorioPublicarPerdido;
    }


    @Override
    public void publicarPerdido(Perdido perdido) throws PerdidoExeption {
             repositorioPublicarPerdido.guardarPerdido(perdido);
    }
}
