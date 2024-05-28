package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.servicios.interfaces.ServicioEditar;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioEditarHistoriaImp implements ServicioEditar {
    private RepositorioPublicacionImpl repositorioPublicacionImp;

    @Autowired
    public ServicioEditarHistoriaImp(SessionFactory sessionFactory) {
        this.repositorioPublicacionImp = new RepositorioPublicacionImpl(sessionFactory);
    }

    @Override
    public void editarHistoria(Long idPublicacion, String titular, String nombreMascota, Zona zona, String descripcion, byte[] imagenBytes) {
        this.repositorioPublicacionImp.editarHistoria(idPublicacion, titular, nombreMascota, zona, descripcion, imagenBytes);

    }

    @Override
    public void editarPerdido(Long idPublicacion, String nombreMascota, Long telefonoContacto, String nombreContacto, MascotaColor mascotaColor, MascotaRaza mascotaRaza, PublicacionTipo tipoPublicacion, Zona zona, String descripcion, String direccion, byte[] imagen) {

    }

    @Override
    public void editarDonacion(Long idPublicacion, String nombreMascota, Double montoACubrir, Zona zona, String descripcion, byte[] imagen) {

    }

}
