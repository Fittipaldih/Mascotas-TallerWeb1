package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
