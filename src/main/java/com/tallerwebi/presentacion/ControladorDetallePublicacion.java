package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionDonacion;
import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.PublicacionPerdido;
import com.tallerwebi.dominio.servicios.ServicioDetallePublicacionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
public class ControladorDetallePublicacion {

    @Autowired
    private ServicioDetallePublicacionImpl servicioDetallePublicacion;

    @RequestMapping(value = "/detalle-publicacion", method = RequestMethod.GET)
    public ModelAndView mostrarDetallePublicacion(@RequestParam Long id){
        ModelMap model = new ModelMap();
        try {
            Publicacion publicacion = servicioDetallePublicacion.getPublicacion(id);
            model.put("publicacionData", publicacion);

            if (publicacion instanceof PublicacionHistoria) {
                PublicacionHistoria historia = (PublicacionHistoria) publicacion;
                model.put("historia", historia);
            } else if (publicacion instanceof PublicacionDonacion) {
                PublicacionDonacion donacion = (PublicacionDonacion) publicacion;
                model.put("donacion", donacion);
            } else if (publicacion instanceof PublicacionPerdido) {
                PublicacionPerdido perdido = (PublicacionPerdido) publicacion;
                model.put("perdido", perdido);
            }

            return new ModelAndView("detalle-publicacion", model);
        } catch (Exception e) {
            model.put("mensaje", e.getMessage());
            return new ModelAndView("home", model);
        }
    }

}

