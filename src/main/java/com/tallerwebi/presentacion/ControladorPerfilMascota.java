package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.excepcion.MascotaNoEncontrada;
import com.tallerwebi.dominio.servicios.interfaces.ServicioPerfilMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NoResultException;

@Controller
@Transactional
public class ControladorPerfilMascota {

    private final ServicioPerfilMascota servicioPerfilMascota;

    @Autowired
    public ControladorPerfilMascota(ServicioPerfilMascota servicioPerfilMascota) {
        this.servicioPerfilMascota = servicioPerfilMascota;
    }

    @RequestMapping(value = "/perfil-mascota", method = RequestMethod.GET)
    public ModelAndView mostrarPerfilMascota(@RequestParam Long id){
        ModelMap model = new ModelMap();
        try {
            Mascota mascota = servicioPerfilMascota.buscarMascotaPorId(id);
            model.put("mascotaData", mascota);
            return new ModelAndView("perfil-mascota", model);
        } catch (NoResultException | MascotaNoEncontrada e) {
            model.put("mensaje", "No se encontró la mascota que buscás. Intentá nuevamente");
            return new ModelAndView("home", model);
        }
    }

}

