package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.servicios.ServicioPublicacionHistoriaImpl;
import com.tallerwebi.dominio.Zona;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorPublicacionHistoria {
    private ServicioPublicacionHistoriaImpl servicioHistorias;

    public ControladorPublicacionHistoria() {
        this.servicioHistorias= new ServicioPublicacionHistoriaImpl();
    }

    @RequestMapping(value = "/historiasFilter", method = RequestMethod.GET)
    public ModelAndView filtrarHistorias(@RequestParam (value = "zona") Zona zona) {
        ModelMap model = new ModelMap();
        List<PublicacionHistoria> historiasFiltradas = new ArrayList<>();

        historiasFiltradas.addAll(this.servicioHistorias.filtrarHistoriasPorZona(zona));
        model.put("historiasFiltradas", historiasFiltradas);
        return new ModelAndView("redirect:/historias",model);
    }
}
