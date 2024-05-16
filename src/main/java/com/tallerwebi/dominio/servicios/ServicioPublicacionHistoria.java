package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.Zona;

import java.util.List;

public interface ServicioPublicacionHistoria {
    List<PublicacionHistoria> filtrarHistoriasPorZona(Zona zona);
}
