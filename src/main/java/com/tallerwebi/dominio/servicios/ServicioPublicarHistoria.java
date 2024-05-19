package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.excepcion.HistoriaException;

public interface ServicioPublicarHistoria {
    void publicarHistoria(PublicacionHistoria historia) throws HistoriaException;
}
