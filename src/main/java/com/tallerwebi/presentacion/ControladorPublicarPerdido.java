package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.servicios.ServicioPublicacionConversion;
import com.tallerwebi.dominio.servicios.interfaces.ServicioPublicarPerdido;
import com.tallerwebi.dominio.servicios.ServicioRedSocialImpl;
import com.tallerwebi.dominio.servicios.interfaces.ServicioRedSocial;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;


@Controller
@Transactional
public class ControladorPublicarPerdido {

    private final ServicioPublicarPerdido servicioPublicarPerdido;
    private final ServicioRedSocial servicioRedSocial;
    private final ServicioPublicacionConversion publicacionConversionService;
    private final ControladorPublicar controladorPublicar;

    @Autowired
    public ControladorPublicarPerdido(ServicioPublicarPerdido servicioPublicarPerdido, ServicioRedSocial servicioRedSocial, ServicioPublicacionConversion publicacionConversionService, ControladorPublicar controladorPublicar) {
        this.servicioPublicarPerdido = servicioPublicarPerdido;
        this.servicioRedSocial = servicioRedSocial;
        this.publicacionConversionService = publicacionConversionService;
        this.controladorPublicar = controladorPublicar;
    }

    @RequestMapping(value = "/nuevo-perdido", method = RequestMethod.POST)
    public ModelAndView publicarPerdido(@RequestParam(value = "direccion") String direccion,
                                        @RequestParam(value = "nombre") String nombreMascota,
                                        @RequestParam(value = "zona") Zona zona,
                                        @RequestParam(value = "mascotaColor") MascotaColor mascotaColor,
                                        @RequestParam(value = "descripcion") String descripcion,
                                        @RequestParam(value = "nombreContacto") String nombreContacto,
                                        @RequestParam(value = "telefonoContacto") Long telefonoContacto,
                                        @RequestParam(value = "tipoPublicacionPerdido") PublicacionTipo tipoPublicacionPerdido,
                                        @RequestParam(value = "mascotaRaza") MascotaRaza mascotaRaza,
                                        @RequestParam(value = "imagen", required = false) MultipartFile imagen
                                        ) {
        ModelMap modelMap = new ModelMap();
        try {
            byte[] imagenBytes = null;
            if(imagen != null && !imagen.isEmpty()){
                imagenBytes = imagen.getBytes();
            }
            PublicacionPerdido perdido = new PublicacionPerdido(nombreMascota, direccion, nombreContacto, zona, mascotaColor, descripcion, telefonoContacto, tipoPublicacionPerdido, mascotaRaza, imagenBytes);
            servicioPublicarPerdido.publicarPerdido(perdido, imagen);
            return this.controladorPublicar.getModelAndView(modelMap, (ServicioRedSocialImpl) servicioRedSocial, publicacionConversionService);
        } catch (Exception e) {
            modelMap.put("error", "Error al publicar la mascota perdida. Intentá nuevamente.");
            return new ModelAndView("publicar", modelMap);
        }
    }

}