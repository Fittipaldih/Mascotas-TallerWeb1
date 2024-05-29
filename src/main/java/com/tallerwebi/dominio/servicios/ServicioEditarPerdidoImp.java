package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.PublicacionInexistenteExeption;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioEditarPerdidoImp implements ServicioEditar {
    @Autowired
    private RepositorioPublicacionImpl repositorioPublicacionImp;
    @Autowired
    public ServicioEditarPerdidoImp(RepositorioPublicacionImpl repositorioPublicacionImp) {
        this.repositorioPublicacionImp = repositorioPublicacionImp;
    }

    @Override
    public void editarHistoria(Long idPublicacion, String titular, String nombreMascota, Zona zona, String descripcion, byte[] imagenBytes) {

    }

    @Override
    public void editarPerdido(Long idPublicacion, String nombreMascota, Long telefonoContacto,
                              String nombreContacto, MascotaColor mascotaColor, MascotaRaza mascotaRaza,
                              PublicacionTipo tipoPublicacion, Zona zona, String descripcion,
                              String direccion, byte[] imagen) throws PublicacionInexistenteExeption {

        Publicacion publicacionBuscada = this.repositorioPublicacionImp.getPublicacionPorId(idPublicacion);
        if (publicacionBuscada instanceof PublicacionPerdido){
            this.repositorioPublicacionImp.editarPerdido(idPublicacion,nombreMascota,telefonoContacto,
                    nombreContacto,mascotaColor,mascotaRaza,tipoPublicacion,zona,descripcion,direccion,imagen);
        }else {
            throw new PublicacionInexistenteExeption() ;
        }
    }

    @Override
    public void editarDonacion(Long idPublicacion, String nombreMascota, Double montoACubrir, Zona zona, String descripcion, byte[] imagen) {

    }


}
