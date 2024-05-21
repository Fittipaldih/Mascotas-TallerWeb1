package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ControladorRedSocial {

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
