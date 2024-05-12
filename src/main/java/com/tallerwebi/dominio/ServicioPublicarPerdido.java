package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.PerdidoExeption;

public interface ServicioPublicarPerdido {
     void publicarPerdido(Perdido perdido) throws PerdidoExeption;
}
