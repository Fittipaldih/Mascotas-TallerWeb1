package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.ServicioPerfilMascotaImpl;
import com.tallerwebi.dominio.excepcion.MascotaNoEncontrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
public class ControladorPerfilMascota {
    @Autowired
    private ServicioPerfilMascotaImpl servicioPerfilMascota;

    /*
        @RequestMapping("/perfil-mascota/{id}")
        public ModelAndView mostrarPerfilMascota(@PathVariable Long id) throws MascotaNoEncontrada {
            Mascota mascota = servicioPerfilMascota.buscarMascotaPorIdPublicacion(id);
            model.put("mascota", mascota);
            return "perfil-mascota";
        }

     */
    @RequestMapping("/perfil-mascota/{id}")
    public ModelAndView mostrarPerfilMascota(@PathVariable Long id) throws MascotaNoEncontrada {
        ModelMap model = new ModelMap();

        try {
            Mascota mascota = servicioPerfilMascota.buscarMascotaPorIdPublicacion(id);
            model.put("mascotaData", mascota);
            return new ModelAndView("perfil-mascota", model);
        } catch (MascotaNoEncontrada e) {
            model.put("error", "Mascota no encontrada");
            return new ModelAndView("error", model);
        }
    }
}

    /*@RequestMapping(value = "/perfil-mascota", method = RequestMethod.GET)
    public ModelAndView irAlPerfilMascota() {
        return new ModelAndView("perfil-mascota");
    }

    @RequestMapping("/perfil-mascota")
    public ModelAndView verPerfilMascota(@RequestParam("idPublicacion") Long idPublicacion) {
        ModelMap model = new ModelMap();

        try {
            Mascota mascota = servicioPerfilMascota.buscarMascotaPorIdPublicacion(idPublicacion);
            model.put("mascotaData", mascota);
            return new ModelAndView("perfil-mascota", model);
        } catch (MascotaNoEncontrada e) {
            model.put("error", "Mascota no encontrada");
            return new ModelAndView("error", model);
        }
    }

}*/
