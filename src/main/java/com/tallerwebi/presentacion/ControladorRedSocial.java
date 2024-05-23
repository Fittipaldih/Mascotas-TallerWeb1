package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionDTO;
import com.tallerwebi.dominio.servicios.ServicioPublicacionConversion;
import com.tallerwebi.dominio.servicios.ServicioRedSocial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class ControladorRedSocial {

    @Autowired
    ServicioRedSocial servicioRedSocial;
    @Autowired
    ServicioPublicacionConversion publicacionConversionService;

    @RequestMapping(value = "/red-social", method = RequestMethod.GET)
    public ModelAndView mostrarTodasLasPublicaciones(){
        ModelMap model = new ModelMap();
        List<Publicacion> todasLasPublicaciones = servicioRedSocial.getTodasLasPublicaciones();
        List<PublicacionDTO> todasLasPublicacionesDTO = publicacionConversionService.convertirEntidadesADTOs(todasLasPublicaciones);
        model.put("todasLasPublicaciones", todasLasPublicacionesDTO);
        return new ModelAndView("red-social", model);
    }

    @RequestMapping(value = "/publicar", method = RequestMethod.GET)
    public ModelAndView publicar() {
        return new ModelAndView("publicar");
    }

    @RequestMapping(value = "/perdidos", method = RequestMethod.GET)
    public ModelAndView IrAperdidos() {
        return new ModelAndView("perdidos");
    }

    @RequestMapping(value = "/historias", method = RequestMethod.GET)
    public ModelAndView irAHistorias() {
        return new ModelAndView("historias");
    }

    @RequestMapping(value = "/donaciones", method = RequestMethod.GET)
    public ModelAndView irADonaciones() {
        return new ModelAndView("donaciones");
    }

}
