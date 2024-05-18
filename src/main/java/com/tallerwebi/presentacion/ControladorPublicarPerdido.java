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

import javax.transaction.Transactional;

@Controller
@Transactional
public class ControladorPublicarPerdido {

    @Autowired
    private ServicioPublicarPerdidoImp servicioPublicarPerdidoImp;
/*
    @RequestMapping(value = "/nuevo-perdido", method = RequestMethod.POST)
    public ModelAndView publicarPerdido(@RequestParam(value = "direccion") String direccion,
                                         @RequestParam(value = "nombre") String nombreMascota,
                                          @RequestParam(value = "zona") Zona zona,
                                          @RequestParam(value = "mascotaColor") MascotaColor mascotaColor,
                                          @RequestParam(value = "descripcion") String descripcion,
                                          @RequestParam(value = "nombreContacto") String nombreContacto,
                                          @RequestParam(value = "telefonoContacto") Integer telefonoContacto
    ) throws PerdidoException {
        PublicacionPerdido perdido = new PublicacionPerdido(nombreMascota, direccion, nombreContacto, zona, mascotaColor, descripcion, telefonoContacto);
        servicioPublicarPerdidoImp.publicarPerdido(perdido);

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("perdido", perdido);
        modelMap.put("mensaje","La publicacion ha sido publicada con exito");
        return new ModelAndView("redirect:/publicarPerdido",modelMap);
    }
*/
    @RequestMapping(value = "/nuevo-perdido", method = RequestMethod.POST)
    public ModelAndView publicarPerdido(@RequestParam(value = "direccion") String direccion,
                                        @RequestParam(value = "nombre") String nombreMascota,
                                        @RequestParam(value = "zona") Zona zona,
                                        @RequestParam(value = "mascotaColor") MascotaColor mascotaColor,
                                        @RequestParam(value = "descripcion") String descripcion,
                                        @RequestParam(value = "nombreContacto") String nombreContacto,
                                        @RequestParam(value = "telefonoContacto") Long telefonoContacto,
                                        @RequestParam(value = "tipoPublicacionPerdido") PublicacionTipo tipoPublicacionPerdido
    ) throws PerdidoException {
        ModelMap modelMap = new ModelMap();
        try {
            PublicacionPerdido perdido = new PublicacionPerdido(nombreMascota, direccion, nombreContacto, zona, mascotaColor, descripcion, telefonoContacto, tipoPublicacionPerdido);
            servicioPublicarPerdidoImp.publicarPerdido(perdido);
            modelMap.put("mensaje", "La publicación ha sido creada exitosamente");
            return new ModelAndView("publicarPerdido", modelMap);
        } catch (PerdidoException e) {
            modelMap.put("error", "Error al publicar la mascota perdida: " + e.getMessage());
            return new ModelAndView("publicarPerdido", modelMap);
        }
    }

}