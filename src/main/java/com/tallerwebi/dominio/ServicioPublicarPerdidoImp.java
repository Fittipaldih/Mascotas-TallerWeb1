package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.PerdidoExeption;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import com.tallerwebi.infraestructura.RepositorioPublicacionesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPublicarPerdidoImp implements ServicioPublicarPerdido {

    private RepositorioPublicacionesImp repositorioPublicarPerdido;

    @Autowired
    public  ServicioPublicarPerdidoImp(RepositorioPublicacionesImp repositorioPublicarPerdido) {
            this.repositorioPublicarPerdido = repositorioPublicarPerdido;
    }


    @Override
    public void publicarPerdido(Perdido perdido) throws PerdidoExeption {
             repositorioPublicarPerdido.guardarPerdido(perdido);
    }
}
