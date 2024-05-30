package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionDTO;
import com.tallerwebi.dominio.servicios.ServicioPublicacionConversion;
import com.tallerwebi.dominio.servicios.ServicioRedSocialImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
public class ControladorPublicar {

    @RequestMapping(value = "/publicarPerdido", method = RequestMethod.GET)
    public ModelAndView irPublicarPerdido() {
        return new ModelAndView("publicarPerdido");
    }

    @RequestMapping(value = "/publicarHistoria", method = RequestMethod.GET)
    public ModelAndView irPublicarHistoria() {
        return new ModelAndView("publicarHistoria");
    }

    @RequestMapping(value = "/publicarDonacion", method = RequestMethod.GET)
    public ModelAndView irPublicarDonacion() {
        return new ModelAndView("publicarDonacion");
    }

    // Generamos este metodo aca porque se utiliza en los otros 3 ControladoresPublicar
    public ModelAndView getModelAndView(ModelMap modelMap, ServicioRedSocialImpl servicioRedSocial, ServicioPublicacionConversion publicacionConversionService) {
        List<Publicacion> todasLasPublicaciones = servicioRedSocial.getTodasLasPublicaciones();
        Collections.reverse(todasLasPublicaciones);
        List<PublicacionDTO> todasLasPublicacionesDTO = publicacionConversionService.convertirEntidadesADTOs(todasLasPublicaciones);
        modelMap.put("todasLasPublicaciones", todasLasPublicacionesDTO);
        modelMap.put("mensaje", "¡La publicación ha sido creada exitosamente!");
        return new ModelAndView("red-social", modelMap);
    }
}
