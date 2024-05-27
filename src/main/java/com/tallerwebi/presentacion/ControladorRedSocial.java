package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionDTO;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.servicios.ServicioPublicacionConversion;
import com.tallerwebi.dominio.servicios.ServicioRedSocial;
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
        return new ModelAndView("red-social", model);
    }

    @RequestMapping(value = "/editar-publicacion", method = RequestMethod.GET)
    public ModelAndView editarPublicacion(@RequestParam(value = "tipoPublicacion") PublicacionTipo publicacionTipo,
                                            @RequestParam(value = "idPublicacion") Long idPublicacion) {
        ModelMap model = new ModelMap();
        String seccionEditar = this.servicioRedSocial.getSeccionEditar(publicacionTipo);
        Publicacion publicacionBuscada =  this.servicioRedSocial.getPublicacionPorId(idPublicacion);

        model.addAttribute("publicacionBuscada", publicacionBuscada);
//        model.put("mensaje", "Debe ser algo asi");

        return new ModelAndView("editar-" + seccionEditar,model);
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
