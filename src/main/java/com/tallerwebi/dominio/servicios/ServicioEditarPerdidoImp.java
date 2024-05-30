package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.MascotaColor;
import com.tallerwebi.dominio.MascotaRaza;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import com.tallerwebi.dominio.servicios.interfaces.ServicioEditar;
import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.PublicacionInexistenteExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ServicioEditarPerdidoImp implements ServicioEditar {

    private final RepositorioPublicacion repositorioPublicacion;

    @Autowired
    public ServicioEditarPerdidoImp(RepositorioPublicacion repositorioPublicacionImp) {
        this.repositorioPublicacion = repositorioPublicacionImp;
    }

    @Override
    public void editarPerdido(Long idPublicacion, String nombreMascota, Long telefonoContacto,
                              String nombreContacto, MascotaColor mascotaColor, MascotaRaza mascotaRaza,
                              PublicacionTipo tipoPublicacion, Zona zona, String descripcion,
                              String direccion, byte[] imagen) throws PublicacionInexistenteExeption {

        Publicacion publicacionBuscada = this.repositorioPublicacion.getPublicacionPorId(idPublicacion);
        if (publicacionBuscada instanceof PublicacionPerdido){
            this.repositorioPublicacion.editarPerdido(idPublicacion,nombreMascota,telefonoContacto,
                    nombreContacto,mascotaColor,mascotaRaza,tipoPublicacion,zona,descripcion,direccion,imagen);
        }else {
            throw new PublicacionInexistenteExeption() ;
        }
    }

    @Override
    public void editarHistoria(Long idPublicacion, String titular, String nombreMascota, Zona zona, String descripcion, byte[] imagenBytes) {

    }

    @Override
    public void editarDonacion(Long idPublicacion, String nombreMascota, Double montoACubrir, Zona zona, String descripcion, byte[] imagen) {

    }


}
