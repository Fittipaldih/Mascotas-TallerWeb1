package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.Peluqueria;
import com.tallerwebi.dominio.Veterinaria;
import com.tallerwebi.dominio.servicios.ServicioMapaMascoteroImpl;
import com.tallerwebi.dominio.servicios.interfaces.ServicioMapaMascotero;
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

    private final ServicioMapaMascotero servicioMapaMascotero;

    @Autowired
    public ControladorMapaMascotero (ServicioMapaMascotero servicioMapaMascotero) {
        this.servicioMapaMascotero = servicioMapaMascotero;
    }

    @Transactional
    @RequestMapping("/mapa-mascotero")
    public ModelAndView mostrarMapaOriginal(@RequestParam(value = "zona", required = false, defaultValue = "default") String zona) {
        ModelMap modelMap = new ModelMap();
        return new ModelAndView("mapa-mascotero", modelMap);
    }

    @Transactional
    @RequestMapping("/mapa")
    public ModelAndView mostrarMapa(@RequestParam(value = "zona", required = false, defaultValue = "default") String zona) {
        ModelMap modelMap = new ModelMap();

        String mapaSrc = null;
        switch (zona) {
            case "oeste":
                mapaSrc = "https://www.google.com/maps/embed?pb=!1m16!1m12!1m3!1d105015.90483118463!2d-58.66142060931234!3d-34.66109117037397!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!2m1!1sveterinarias%20zona%20oeste!5e0!3m2!1ses-419!2sar!4v1719156406289!5m2!1ses-419!2sar";
                break;
            case "norte":
                mapaSrc = "https://www.google.com/maps/embed?pb=!1m16!1m12!1m3!1d210491.93298418113!2d-58.696273392352474!3d-34.4791363003702!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!2m1!1sveterinarias%20zona%20norte!5e0!3m2!1ses-419!2sar!4v1719156785476!5m2!1ses-419!2sar";
                break;
            case "sur":
                mapaSrc = "https://www.google.com/maps/embed?pb=!1m16!1m12!1m3!1d209747.92788845912!2d-58.476287936121594!3d-34.772935860754295!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!2m1!1sveterinarias%20zona%20sur!5e0!3m2!1ses-419!2sar!4v1719156913964!5m2!1ses-419!2sar";
                break;
            case "caba":
                mapaSrc = "https://www.google.com/maps/embed?pb=!1m16!1m12!1m3!1d88362.75813923574!2d-58.49099455036968!3d-34.60920221816825!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!2m1!1sveterinarias%20CABA!5e0!3m2!1ses-419!2sar!4v1719156874391!5m2!1ses-419!2sar";
                break;
            case "default":
            case "original":
                mapaSrc = "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7655.769728206894!2d-58.570091273631924!3d-34.66906268820045!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x95bcc74d2a85f6b7%3A0xf7b42458c4ff6819!2sUNLaM%20-%20Mesa%20de%20Entrada!5e0!3m2!1ses-419!2sar!4v1714858059151!5m2!1ses-419!2sar";
                break;
        }

        modelMap.put("mapaSrc", mapaSrc);
        return new ModelAndView("mapa-mascotero", modelMap);
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
