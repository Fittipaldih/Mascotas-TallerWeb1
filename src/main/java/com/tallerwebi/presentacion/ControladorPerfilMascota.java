package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.ServicioPerfilMascotaImpl;
import com.tallerwebi.dominio.excepcion.MascotaNoEncontrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorPerfilMascota {

    private com.tallerwebi.dominio.ServicioPerfilMascota servicioPerfilMascota;
    private ServicioPerfilMascotaImpl ServicioPerfilMascota;

    @Autowired
    public ControladorPerfilMascota(ServicioPerfilMascotaImpl servicioPerfilMascota) {
        this.servicioPerfilMascota = servicioPerfilMascota;
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
*/
}

