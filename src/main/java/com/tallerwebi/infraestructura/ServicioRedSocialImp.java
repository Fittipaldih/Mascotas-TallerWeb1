package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;

import java.util.List;

public class ServicioRedSocialImp implements ServicioRedSocial {

    @Override
    public List<Publicacion> filtrarPublicacion(TipoPublicacion tipoPublicacion, Zona zona, ColorPelo pelo, TiempoBusqueda tiempoBusqueda) {
        return List.of();
        // a partir de todas las publicaciones debo filtrar segun los parametros
        // como pido al repositorio todas las publicaciones para filtrarlas
    }
}
