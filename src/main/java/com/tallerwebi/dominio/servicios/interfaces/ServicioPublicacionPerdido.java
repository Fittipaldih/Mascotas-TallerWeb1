package com.tallerwebi.dominio.servicios.interfaces;

import com.tallerwebi.dominio.PublicacionPerdido;
import com.tallerwebi.dominio.PublicacionTiempo;
import com.tallerwebi.dominio.Zona;

import java.util.List;

public interface ServicioPublicacionPerdido {
   List<PublicacionPerdido> filtrarPublicacionPerdidos(Zona zona, PublicacionTiempo tiempoPublicacion, String colorPelo);

}
