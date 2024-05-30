package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionDTO;
import com.tallerwebi.dominio.PublicacionTiempo;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.servicios.ServicioPublicacionConversion;
import com.tallerwebi.dominio.servicios.interfaces.ServicioRedSocial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Controller
@Transactional
public class ControladorRedSocial {

    @Autowired
    ServicioRedSocial servicioRedSocial;
    @Autowired
    ServicioPublicacionConversion publicacionConversionService;

    @RequestMapping(value = "/red-social", method = RequestMethod.GET)
    public ModelAndView mostrarYFiltrarPublicaciones(@RequestParam(value = "ordenar", required = false, defaultValue = "ASC") String ordenar,
                                                     @RequestParam(value = "zona", required = false) Zona zona,
                                                     @RequestParam(value = "nombre", required = false) String nombre) {
        ModelMap model = new ModelMap();
        List<Publicacion> publicaciones = servicioRedSocial.getPublicacionesSegunFiltros(zona, nombre);;

        if ("ASC".equalsIgnoreCase(ordenar)) {
            Collections.reverse(publicaciones);
        }
        List<PublicacionDTO> todasLasPublicacionesDTO = publicacionConversionService.convertirEntidadesADTOs(publicaciones);
        model.put("todasLasPublicaciones", todasLasPublicacionesDTO);
        return new ModelAndView("red-social", model);
    }

    @RequestMapping(value = "/publicar", method = RequestMethod.GET)
    public ModelAndView publicar() {
        return new ModelAndView("publicar");
    }

    @RequestMapping(value = "/perdidos", method = RequestMethod.GET)
    public ModelAndView mostrarPublicacionesPerdidos() {
        ModelMap model = new ModelMap();
        List<Publicacion> publicacionesPerdidos = servicioRedSocial.getPublicacionesTipoPerdido();
        List<PublicacionDTO> publicacionesPerdidosDTO = publicacionConversionService.convertirEntidadesADTOs(publicacionesPerdidos);
        model.put("todasLasPublicaciones", publicacionesPerdidosDTO);
        return new ModelAndView("perdidos", model);
    }

    @RequestMapping(value = "/historias", method = RequestMethod.GET)
    public ModelAndView mostrarPublicacionesHistorias() {
        ModelMap model = new ModelMap();
        List<Publicacion> publicacionesHistorias = servicioRedSocial.getPublicacionesTipoHistoria();
        List<PublicacionDTO> publicacionesHistoriasDTO = publicacionConversionService.convertirEntidadesADTOs(publicacionesHistorias);
        model.put("todasLasPublicaciones", publicacionesHistoriasDTO);
        return new ModelAndView("historias", model);
    }

    @RequestMapping(value = "/donaciones", method = RequestMethod.GET)
    public ModelAndView mostrarPublicacionesDonaciones() {
        ModelMap model = new ModelMap();
        List<Publicacion> publicacionesDonaciones = servicioRedSocial.getPublicacionesTipoDonacion();
        List<PublicacionDTO> publicacionesDonacionesDTO = publicacionConversionService.convertirEntidadesADTOs(publicacionesDonaciones);
        model.put("todasLasPublicaciones", publicacionesDonacionesDTO);
        return new ModelAndView("donaciones", model);
    }

}
