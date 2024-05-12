package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioPublicacionesImp;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ServicioHistoriasImp implements ServicioHistorias {

    private RepositorioPublicacionesImp repositorioPublicacionesImp;
    private SessionFactory sessionFactory;

    public ServicioHistoriasImp() {
        this.repositorioPublicacionesImp = new RepositorioPublicacionesImp(sessionFactory);
    }

    @Override
    public List<Historia> filtrarHistoriasPorZona(Zona zona) {
        List<Historia> historiasFiltradas = new ArrayList<>();
        List<Historia> historias = new ArrayList<>();
        historias.addAll(obtenerSoloHistorias(this.repositorioPublicacionesImp.getPublicaciones()));

        for (Historia historia : historias) {
            if (zona.equals(historia.getZona())) {
                historiasFiltradas.add(historia);
            }
        }
        return historiasFiltradas;
    }

    private static List<Historia> obtenerSoloHistorias(List<Publicacion> todasPublicaciones) {
        List<Historia> historiasFiltradas = new ArrayList<>();
        for (Publicacion publicacion : todasPublicaciones) {
            if (publicacion instanceof Historia) {
                historiasFiltradas.add((Historia) publicacion);
            }
        }
        return historiasFiltradas;
    }
}
