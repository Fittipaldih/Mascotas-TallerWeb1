package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.PerdidoException;
import com.tallerwebi.dominio.servicios.ServicioPublicarPerdidoImp;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorPublicarPerdido {

    private ServicioPublicarPerdidoImp servicioPublicarPerdidoImp;

    @Autowired
    public void ControladorPublicarPerdido (ServicioPublicarPerdidoImp servicioPublicarPerdidoImp) {
        this.servicioPublicarPerdidoImp = servicioPublicarPerdidoImp;
    }


    @RequestMapping(value = "/nuevo-perdido", method = RequestMethod.POST)
    public ModelAndView crearNuevoPerdido(@RequestParam(value = "direccion") String direccion,
                                          @RequestParam(value = "zona") Zona zona,
                                          @RequestParam(value = "colorPelo") MascotaColor colorPelo,
                                          @RequestParam(value = "descripcion") String descripcion,
                                          @RequestParam(value = "nombreContacto") String nombreContacto,
                                          @RequestParam(value = "numeroContacto") Integer numeroContacto
    ) throws PerdidoException {
        PublicacionPerdido perdido = new PublicacionPerdido(direccion, nombreContacto, numeroContacto, zona, colorPelo, descripcion, numeroContacto);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("perdido", perdido);
        modelMap.put("mensaje","La publicacion ha sido publicada con exito");
        servicioPublicarPerdidoImp.publicarPerdido(perdido);
        return new ModelAndView("redirect:/publicarPerdido",modelMap);
    }
}
