package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
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
    private ServicioPerdidosImp servicioPerdidosImp;

    public ControladorPerdidos() {
        this.servicioPerdidosImp = new ServicioPerdidosImp();
    }

    @RequestMapping(value = "/perdidosFilter", method = RequestMethod.GET)
        public ModelAndView filtrarPublicacion(@RequestParam(value = "zona") Zona zona,
                                               @RequestParam(value = "tiempoPublicacion") TiempoPublicacion tiempoPublicacion,
                                               @RequestParam(value = "colorPelo") String colorPelo
                                                ){
        List<Perdido> perdidosFiltrados = new ArrayList<>();
        ModelMap model = new ModelMap();
        ModelAndView modelAndView = new ModelAndView();

        perdidosFiltrados.addAll(this.servicioPerdidosImp.filtrarPublicacionPerdidos(zona,tiempoPublicacion,colorPelo));
        model.put("perdidosFiltrados", perdidosFiltrados);
        return new ModelAndView("redirect:/perdidos",model);    }
}