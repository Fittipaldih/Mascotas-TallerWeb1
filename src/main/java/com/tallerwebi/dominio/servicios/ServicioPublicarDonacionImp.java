package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionDTO;
import com.tallerwebi.dominio.PublicacionDonacion;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.excepcion.DonacionException;
import com.tallerwebi.dominio.servicios.interfaces.ServicioPublicarDonacion;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ServicioPublicarDonacionImp implements ServicioPublicarDonacion {
    @Autowired
    private RepositorioPublicacionImpl repositorioPublicarDonacion;
    private SessionFactoryImpl sessionFactory;
    private ServicioPublicacionConversion publicacionConversionService;

    @Override
    public void publicarDonacion(PublicacionDonacion donacion, MultipartFile imagen) throws DonacionException {
        if(imagen != null && !imagen.isEmpty()){
            try {
                donacion.setImagen(imagen.getBytes());
            } catch (IOException e){
                throw new RuntimeException("Error al procesar la imagen", e);
            }
        }
        donacion.setTipoPublicacion(PublicacionTipo.DONACION);
        this.repositorioPublicarDonacion.guardarDonacion(donacion);
    }

    public List<PublicacionDTO> obtenerTodasLasDonaciones() {
        List<Publicacion> publicaciones = repositorioPublicarDonacion.getPublicacionesPorTipoPublicacion(PublicacionTipo.DONACION);
        return publicacionConversionService.convertirEntidadesADTOs(publicaciones);
    }
}
