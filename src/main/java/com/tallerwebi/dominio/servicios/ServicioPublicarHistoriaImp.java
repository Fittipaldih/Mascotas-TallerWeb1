package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.PublicacionPerdido;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.excepcion.HistoriaException;
import com.tallerwebi.dominio.excepcion.PerdidoException;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ServicioPublicarHistoriaImp implements ServicioPublicarHistoria {


    @Autowired
    private  RepositorioPublicacionImpl repositorioPublicarHistoria;
    SessionFactoryImpl sessionFactory;

    public ServicioPublicarHistoriaImp() {
           this.repositorioPublicarHistoria = new RepositorioPublicacionImpl(sessionFactory);
    }

    @Override
    public void publicarHistoria(PublicacionHistoria historia, MultipartFile imagen) throws HistoriaException {
        if(imagen != null && !imagen.isEmpty()){
            try {
                historia.setImagen(imagen.getBytes());
            } catch (IOException e){
                throw new RuntimeException("Error al procesar la imagen", e);
            }
        }
        historia.setTipoPublicacion(PublicacionTipo.HISTORIA);
        repositorioPublicarHistoria.guardarHistoria(historia);
    }
}
