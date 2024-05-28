package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.MascotaColor;
import com.tallerwebi.dominio.MascotaRaza;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioEditarPerdidoImp implements ServicioEditar {

    private RepositorioPublicacionImpl repositorioPublicacionImp;

    @Autowired
    public ServicioEditarPerdidoImp(SessionFactory sessionFactory) {
        this.repositorioPublicacionImp = new RepositorioPublicacionImpl(sessionFactory);
    }

    @Override
    public void editarHistoria(Long idPublicacion, String titular, String nombreMascota, Zona zona, String descripcion, byte[] imagenBytes) {

    }

    @Override
    public void editarPerdido(Long idPublicacion, String nombreMascota, Long telefonoContacto, String nombreContacto, MascotaColor mascotaColor, MascotaRaza mascotaRaza, PublicacionTipo tipoPublicacion, Zona zona, String descripcion, String direccion, byte[] imagen) {
        this.repositorioPublicacionImp.editarPerdido(idPublicacion,nombreMascota,telefonoContacto,nombreContacto,mascotaColor,mascotaRaza,tipoPublicacion,zona,descripcion,direccion,imagen);
    }

    @Override
    public void editarDonacion(Long idPublicacion, String nombreMascota, Double montoACubrir, Zona zona, String descripcion, byte[] imagen) {

    }


}