package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
}
