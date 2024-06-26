package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.servicios.interfaces.ServicioPublicacionPerdido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorPerdidos {

    private final ServicioPublicacionPerdido servicioPerdidosImp;

    @Autowired
    public ControladorPerdidos(ServicioPublicacionPerdido servicioPerdidosImp) {
        this.servicioPerdidosImp = servicioPerdidosImp;
    }

    @RequestMapping(value = "/perdidosFilter", method = RequestMethod.GET)
    public ModelAndView filtrarPublicacion(@RequestParam(value = "zona") Zona zona,
                                           @RequestParam(value = "tiempoPublicacion") PublicacionTiempo tiempoPublicacion,
                                           @RequestParam(value = "colorPelo") String colorPelo) {
        List<PublicacionPerdido> perdidosFiltrados = new ArrayList<>();
        ModelMap model = new ModelMap();
        perdidosFiltrados.addAll(this.servicioPerdidosImp.filtrarPublicacionPerdidos(zona, tiempoPublicacion, colorPelo));
        model.put("perdidosFiltrados", perdidosFiltrados);
        return new ModelAndView("redirect:/perdidos", model);
    }
}
