package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.HistoriaException;
import com.tallerwebi.dominio.excepcion.PerdidoException;
import com.tallerwebi.dominio.servicios.ServicioPublicarHistoriaImp;
import com.tallerwebi.dominio.servicios.ServicioPublicarPerdidoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Controller
@Transactional
public class ControladorPublicarHistoria {

    @Autowired
    private ServicioPublicarHistoriaImp servicioPublicarHistoriaImp;

    @RequestMapping(value = "/nueva-historia", method = RequestMethod.POST)
    public ModelAndView publicarHistoria(@RequestParam(value = "titular") String titular,
                                         @RequestParam(value = "nombre") String nombreMascota,
                                         @RequestParam(value = "tipoPublicacionHistoria") PublicacionTipo publicacionTipo,
                                        @RequestParam(value = "zona") Zona zona,
                                        @RequestParam(value = "descripcion") String descripcion
    ) throws HistoriaException {
        ModelMap modelMap = new ModelMap();
        try {
            PublicacionHistoria Historia = new PublicacionHistoria(titular,nombreMascota,zona,descripcion,publicacionTipo);
            servicioPublicarHistoriaImp.publicarHistoria(Historia);
            modelMap.put("mensaje", "¡La publicación ha sido creada exitosamente!");
            return new ModelAndView("publicar", modelMap);
        } catch (HistoriaException e) {
            modelMap.put("error", "Error al publicar la mascota perdida. Intentá nuevamente. ");
            return new ModelAndView("publicar", modelMap);
        }
    }

}