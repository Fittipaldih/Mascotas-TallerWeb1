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

@Controller
@Transactional
public class ControladorPerfilMascota {
    @Autowired
    private ServicioPerfilMascotaImpl servicioPerfilMascota;

    @RequestMapping("/perfil-mascota/{id}")
    public String mostrarPerfilMascota(@PathVariable Long id) throws MascotaNoEncontrada {
        Mascota mascota = servicioPerfilMascota.buscarMascotaPorIdPublicacion(id);
        ModelMap modelMap = new ModelMap();
        modelMap.put("mascota", mascota);
        return "perfil-mascota";
    }
}
