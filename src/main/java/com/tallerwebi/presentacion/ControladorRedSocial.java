package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ControladorRedSocial {

    @RequestMapping(value = "/filtros", method = RequestMethod.GET)
    public ModelAndView publicacionesFiltradas() {

        ModelAndView filtros = new ModelAndView();

    return null;
    }
}
