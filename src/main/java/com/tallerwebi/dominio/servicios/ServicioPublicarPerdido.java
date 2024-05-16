package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.PublicacionPerdido;
import com.tallerwebi.dominio.excepcion.PerdidoException;

public interface ServicioPublicarPerdido {
     void publicarPerdido(PublicacionPerdido perdido) throws PerdidoException;
}
