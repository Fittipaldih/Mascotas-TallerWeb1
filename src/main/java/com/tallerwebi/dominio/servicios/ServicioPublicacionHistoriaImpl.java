package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.infraestructura.RepositorioMascotaImpl;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ServicioPublicacionHistoriaImpl implements ServicioPublicacionHistoria {

    private RepositorioPublicacionImpl repositorioPublicacionesImp;
    private RepositorioMascotaImpl repositorioMascotaImp;
    private SessionFactory sessionFactory;

    public ServicioPublicacionHistoriaImpl() {
        this.repositorioPublicacionesImp = new RepositorioPublicacionImpl(sessionFactory);
    }

    @Override
    public List<PublicacionHistoria> filtrarHistoriasPorZona(Zona zona) {
        List<PublicacionHistoria> historiasFiltradas = new ArrayList<>();
        List<PublicacionHistoria> historias = new ArrayList<>();
        historias.addAll(obtenerSoloHistorias(this.repositorioPublicacionesImp.getPublicaciones()));

        for (PublicacionHistoria historia : historias) {
            if (zona.equals(historia.getZona())) {
                historiasFiltradas.add(historia);
            }
        }
        return historiasFiltradas;
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
