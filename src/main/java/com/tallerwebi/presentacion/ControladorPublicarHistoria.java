package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.HistoriaException;
import com.tallerwebi.dominio.servicios.ServicioPublicarHistoriaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Controller
@Transactional
public class ControladorPublicarHistoria {

    @Autowired
    private ServicioPublicarHistoriaImp servicioPublicarHistoriaImp = new ServicioPublicarHistoriaImp();

    @RequestMapping(value = "/nueva-historia", method = RequestMethod.POST)
    public ModelAndView publicarHistoria(@RequestParam(value = "titular") String titular,
                                         @RequestParam(value = "nombre") String nombreMascota,
                                         @RequestParam(value = "zona") Zona zona,
                                         @RequestParam(value = "descripcion") String descripcion,
                                         @RequestParam(value = "imagen", required = false) MultipartFile imagen
                                         ) throws HistoriaException {
        ModelMap modelMap = new ModelMap();
        try {
            byte[] imagenBytes = null;
            if(imagen != null && !imagen.isEmpty()){
                imagenBytes = imagen.getBytes();
            }
            PublicacionHistoria historia = new PublicacionHistoria(titular,nombreMascota,zona,descripcion,PublicacionTipo.HISTORIA,imagenBytes);
            servicioPublicarHistoriaImp.publicarHistoria(historia, imagen);
            modelMap.put("mensaje", "¡La publicación ha sido creada exitosamente!");
        } catch (Exception e) {
            modelMap.put("error", "Error al publicar la historia. Intentá nuevamente.");
        }
        return new ModelAndView("publicar", modelMap);
    }

}