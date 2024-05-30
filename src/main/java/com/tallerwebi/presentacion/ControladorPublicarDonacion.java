package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.servicios.ServicioPublicacionConversion;
import com.tallerwebi.dominio.servicios.ServicioPublicarDonacionImp;
import com.tallerwebi.dominio.servicios.ServicioRedSocialImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Controller
@Transactional
public class ControladorPublicarDonacion {

    @Autowired
    private ServicioPublicarDonacionImp servicioPublicarDonacionImp = new ServicioPublicarDonacionImp();
    @Autowired
    ServicioRedSocialImpl servicioRedSocial;
    @Autowired
    ServicioPublicacionConversion publicacionConversionService;
    @Autowired
    ControladorPublicar controladorPublicar;

    @RequestMapping(value = "/nueva-donacion", method = RequestMethod.POST)
    public ModelAndView publicarDonacion(@RequestParam(value = "nombreMascota") String nombreMascota,
                                         @RequestParam(value = "monto") Double monto,
                                         @RequestParam(value = "zona") Zona zona,
                                         @RequestParam(value = "descripcion") String descripcion,
                                         @RequestParam(value = "imagen", required = false) MultipartFile imagen
    ) {
        ModelMap modelMap = new ModelMap();
        try {
            byte[] imagenBytes = null;
            if (imagen != null && !imagen.isEmpty()) {
                imagenBytes = imagen.getBytes();
            }
            PublicacionDonacion donacion = new PublicacionDonacion(monto, PublicacionTipo.DONACION, nombreMascota, zona, descripcion, imagenBytes);
            servicioPublicarDonacionImp.publicarDonacion(donacion, imagen);
            return this.controladorPublicar.getModelAndView(modelMap, servicioRedSocial, publicacionConversionService);
        } catch (Exception e) {
            modelMap.put("error", "Error al publicar la donación. Intentá nuevamente.");
            return new ModelAndView("publicar", modelMap);
        }
    }

}