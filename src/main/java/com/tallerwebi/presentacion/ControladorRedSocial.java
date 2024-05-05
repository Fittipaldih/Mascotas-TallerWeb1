package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class ControladorRedSocial {

    private ServicioRedSocial servicioRedSocial;

    public ControladorRedSocial() {
        this.servicioRedSocial = servicioRedSocial;
    }

    @RequestMapping(value = "/filtros", method = RequestMethod.GET)
    public ModelAndView publicacionesFiltradas(@RequestParam(value = "tipo_publicacion") TipoPublicacion tipoPublicacion,
                                               @RequestParam(value = "zona") Zona zona,
                                               @RequestParam(value = "pelo") ColorPelo pelo,
                                               @RequestParam(value = "tiempo_busqueda") TiempoBusqueda tiempoBusqueda) {

        // Utiliza los datos capturados para realizar operaciones con el servicio de la red social
        List<Publicacion> publicacionesFiltradas = servicioRedSocial.filtrarPublicacion(tipoPublicacion, zona, pelo, tiempoBusqueda);
        ModelMap modelMap = new ModelMap();
        modelMap.put("publicacionesFiltradas", publicacionesFiltradas);

        return new ModelAndView( "red-social",modelMap);


    }
}
