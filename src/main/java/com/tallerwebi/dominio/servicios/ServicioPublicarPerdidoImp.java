package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.PublicacionPerdido;
import com.tallerwebi.dominio.excepcion.PerdidoException;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class ServicioPublicarPerdidoImp implements ServicioPublicarPerdido {

    @Autowired
    private RepositorioPublicacionImpl repositorioPublicarPerdido;

    @Override
    public void publicarPerdido(PublicacionPerdido perdido, MultipartFile imagen) throws PerdidoException {
        if(imagen != null && !imagen.isEmpty()){
            try {
                perdido.setImagen(imagen.getBytes());
            } catch (IOException e){
                throw new RuntimeException("Error al procesar la imagen", e);
            }
        }
             repositorioPublicarPerdido.guardarPerdido(perdido);
    }
}
