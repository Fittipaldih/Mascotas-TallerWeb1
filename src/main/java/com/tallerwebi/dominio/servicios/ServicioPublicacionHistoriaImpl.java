package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import com.tallerwebi.dominio.servicios.interfaces.ServicioPublicacionHistoria;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioPublicacionHistoriaImpl implements ServicioPublicacionHistoria {

    private final RepositorioPublicacion repositorioPublicaciones;

    @Autowired
    public ServicioPublicacionHistoriaImpl(RepositorioPublicacion repositorioPublicaciones) {
        this.repositorioPublicaciones = repositorioPublicaciones;
    }

    @Override
    public List<PublicacionHistoria> filtrarHistoriasPorZona(Zona zona) {
        return repositorioPublicaciones.getPublicacionesHistoriaPorZona(zona);
    }

    private static List<PublicacionHistoria> obtenerSoloHistorias(List<Publicacion> todasPublicaciones) {
        List<PublicacionHistoria> historiasFiltradas = new ArrayList<>();
        for (Publicacion publicacion : todasPublicaciones) {
            if (publicacion instanceof PublicacionHistoria) {
                historiasFiltradas.add((PublicacionHistoria) publicacion);
            }
        }
        return historiasFiltradas;
    }
}
