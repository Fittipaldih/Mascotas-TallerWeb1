package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioPerdidos {
   List<Perdido> filtrarPublicacionPerdidos(Zona zona, TiempoPublicacion tiempoPublicacion, String colorPelo);

}
