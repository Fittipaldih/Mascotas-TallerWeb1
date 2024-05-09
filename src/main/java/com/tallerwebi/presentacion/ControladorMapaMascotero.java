package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioMapaMascoteroImpl;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class ControladorMapaMascotero {

    private ServicioMapaMascoteroImpl servicioMapaMascotero;

    @Autowired
    public ControladorMapaMascotero (ServicioMapaMascoteroImpl servicioMapaMascotero) {
        this.servicioMapaMascotero = servicioMapaMascotero;
    }

    @Transactional
    @RequestMapping("/filtrar")
    public ModelAndView filtrarPublicaciones(
            @RequestParam(value = "tipo_publicacion")
            String tipoPublicacion)
    {

        List<?> publicacionesFiltradas = null;

            switch (tipoPublicacion) {
                case "veterinarias":
                    publicacionesFiltradas = servicioMapaMascotero.getVeterinarias();
                    break;
                case "peluquerias":
                    publicacionesFiltradas = servicioMapaMascotero.getPeluquerias();
                    break;
                case "mascotas":
                    publicacionesFiltradas = servicioMapaMascotero.getMascotas();
                    break;
                default:
                    break;
            }


        ModelMap modelMap = new ModelMap();
        modelMap.put("publicacionesFiltradas", publicacionesFiltradas);

         return new ModelAndView("mapa-mascotero", modelMap);
    }
}
