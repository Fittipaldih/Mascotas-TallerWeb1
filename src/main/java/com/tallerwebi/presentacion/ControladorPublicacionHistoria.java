package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.servicios.ServicioPublicacionHistoriaImpl;
import com.tallerwebi.dominio.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorPublicacionHistoria {
    private ServicioPublicacionHistoriaImpl servicioHistorias;

    @Autowired
    public ControladorPublicacionHistoria(ServicioPublicacionHistoriaImpl servicioHistorias) {
        this.servicioHistorias= new ServicioPublicacionHistoriaImpl();
    }

    @Transactional
    @RequestMapping("/historias-f")
    public ModelAndView filtrarHistorias(
            @RequestParam (value = "zona")
            Zona zona)
    {
        ModelMap model = new ModelMap();
        List<PublicacionHistoria> historias = servicioHistorias.filtrarHistoriasPorZona(zona);
        model.put("historias", historias);
        return new ModelAndView("historias",model);
    }
}

