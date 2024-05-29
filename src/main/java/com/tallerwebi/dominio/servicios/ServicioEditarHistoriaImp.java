package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.servicios.interfaces.ServicioEditar;
import com.tallerwebi.dominio.excepcion.PublicacionInexistenteExeption;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioEditarHistoriaImp implements ServicioEditar {
    @Autowired
    private RepositorioPublicacionImpl repositorioPublicacionImp;

    @Autowired
    public ServicioEditarHistoriaImp(RepositorioPublicacionImpl repositorioPublicacionImp) {
        this.repositorioPublicacionImp = repositorioPublicacionImp;
    }

    @Override
    public void editarHistoria(Long idPublicacion, String titular, String nombreMascota, Zona zona,
                               String descripcion, byte[] imagenBytes) throws PublicacionInexistenteExeption {

        Publicacion publicacionBuscada =this.repositorioPublicacionImp.getPublicacionPorId(idPublicacion);
        if (publicacionBuscada instanceof PublicacionHistoria) {
            this.repositorioPublicacionImp.editarHistoria(idPublicacion, titular, nombreMascota, zona, descripcion, imagenBytes);
        }else{
            throw new PublicacionInexistenteExeption();
        }
    }

    @Override
    public void editarPerdido(Long idPublicacion, String nombreMascota, Long telefonoContacto, String nombreContacto, MascotaColor mascotaColor, MascotaRaza mascotaRaza, PublicacionTipo tipoPublicacion, Zona zona, String descripcion, String direccion, byte[] imagen) {

    }

    @Override
    public void editarDonacion(Long idPublicacion, String nombreMascota, Double montoACubrir, Zona zona, String descripcion, byte[] imagen) {

    }

}
