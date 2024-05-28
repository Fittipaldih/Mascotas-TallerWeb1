package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.servicios.interfaces.ServicioPublicacionHistoria;
import com.tallerwebi.infraestructura.RepositorioMascotaImpl;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioPublicacionHistoriaImpl implements ServicioPublicacionHistoria {

    private RepositorioPublicacionImpl repositorioPublicacionesImp;
    private RepositorioMascotaImpl repositorioMascotaImp;
    private SessionFactory sessionFactory;

    public ServicioPublicacionHistoriaImpl() {
        this.repositorioPublicacionesImp = new RepositorioPublicacionImpl(sessionFactory);
    }

    @Override
    public List<PublicacionHistoria> filtrarHistoriasPorZona(Zona zona) {
        return repositorioPublicacionesImp.getPublicacionesHistoriaPorZona(zona);
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
