package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.Peluqueria;
import com.tallerwebi.dominio.Veterinaria;
import com.tallerwebi.dominio.servicios.ServicioMapaMascoteroImpl;
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
            String filtrarPor)
    {

        ModelMap modelMap = new ModelMap();
            switch (filtrarPor) {
                case "veterinarias":
                    List<Veterinaria> veterinarias = servicioMapaMascotero.getVeterinarias();
                    modelMap.put("veterinarias", veterinarias);
                    break;
                case "peluquerias":
                    List<Peluqueria> peluquerias = servicioMapaMascotero.getPeluquerias();
                    modelMap.put("peluquerias", peluquerias);
                    break;
                case "mascotas":
                    List<Mascota> mascotas = servicioMapaMascotero.getMascotas();
                    modelMap.put("mascotas", mascotas);
                    break;
                default:
                    break;
            }

         return new ModelAndView("mapa-mascotero", modelMap);
    }
}
