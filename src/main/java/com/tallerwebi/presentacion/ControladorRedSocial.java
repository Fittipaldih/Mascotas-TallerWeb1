package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionDTO;
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
    public ModelAndView mostrarTodasLasPublicaciones(@RequestParam(value = "ordenar", required = false, defaultValue = "ASC") String ordenar) {
        ModelMap model = new ModelMap();
        List<Publicacion> todasLasPublicaciones = servicioRedSocial.getTodasLasPublicaciones();
        if ("ASC".equalsIgnoreCase(ordenar)) {
            Collections.reverse(todasLasPublicaciones);
        }
        List<PublicacionDTO> todasLasPublicacionesDTO = publicacionConversionService.convertirEntidadesADTOs(todasLasPublicaciones);
        model.put("todasLasPublicaciones", todasLasPublicacionesDTO);

        List<Publicacion> ultimasPublicaciones = servicioRedSocial.obtenerUltimasPublicaciones(3);
        List<PublicacionDTO> ultimasPublicacionesDTO = publicacionConversionService.convertirEntidadesADTOs(ultimasPublicaciones);
        model.put("ultimasPublicaciones", ultimasPublicacionesDTO);

        return new ModelAndView("red-social", model);
    }

    @RequestMapping(value = "/publicar", method = RequestMethod.GET)
    public ModelAndView publicar() {
        ModelMap model = new ModelMap();
        List<Publicacion> ultimasPublicaciones = servicioRedSocial.obtenerUltimasPublicaciones(3);
        model.addAttribute("ultimasPublicaciones", ultimasPublicaciones);
        return new ModelAndView("publicar", model);
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
