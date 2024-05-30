package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.servicios.ServicioPublicacionConversion;
import com.tallerwebi.dominio.servicios.ServicioRedSocialImpl;
import com.tallerwebi.dominio.servicios.ServicioEditarPerdidoImp;
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
public class ControladorEditarPerdido {
    @Autowired
    private ServicioEditarPerdidoImp servicioEditarPerdidoImp;

    @Autowired
    public ControladorEditarPerdido(ServicioEditarPerdidoImp servicioEditarPerdidoImp) {
        this.servicioEditarPerdidoImp = servicioEditarPerdidoImp;
    }

    @Autowired
    ServicioRedSocialImpl servicioRedSocial;
    @Autowired
    ServicioPublicacionConversion publicacionConversionService;

    @RequestMapping(value = "/editar-perdido", method = RequestMethod.POST)
    public ModelAndView editarPerdido(   @RequestParam(value = "nombre") String nombreMascota,
                                         @RequestParam(value = "nombreContacto") String nombreContacto,
                                         @RequestParam(value = "telefonoContacto") Long telefonoContacto,
                                         @RequestParam(value = "mascotaColor") MascotaColor mascotaColor,
                                         @RequestParam(value = "mascotaRaza") MascotaRaza mascotaRaza,
                                         @RequestParam(value = "idPublicacion") Long idPublicacion,
                                         @RequestParam(value = "tipoPublicacion") PublicacionTipo tipoPublicacion,
                                         @RequestParam(value = "zona") Zona zona,
                                         @RequestParam(value = "descripcion") String descripcion,
                                         @RequestParam(value = "direccion") String direccion,
                                         @RequestParam(value = "imagen", required = false) MultipartFile imagen
    ) {
        ModelMap modelMap = new ModelMap();
        try {
            byte[] imagenBytes = null;
            if (imagen != null && !imagen.isEmpty()) {
                imagenBytes = imagen.getBytes();
            }
            servicioEditarPerdidoImp.editarPerdido(idPublicacion,nombreMascota,telefonoContacto,nombreContacto,mascotaColor,mascotaRaza,tipoPublicacion,zona,descripcion,direccion,imagenBytes);

            List<Publicacion> todasLasPublicaciones = servicioRedSocial.getTodasLasPublicaciones();
            Collections.reverse(todasLasPublicaciones);
            List<PublicacionDTO> todasLasPublicacionesDTO = publicacionConversionService.convertirEntidadesADTOs(todasLasPublicaciones);
            modelMap.put("todasLasPublicaciones", todasLasPublicacionesDTO);
            modelMap.put("mensaje", "¡La publicación ha sido editada exitosamente!");
            return new ModelAndView("red-social", modelMap);
        } catch (Exception e) {
            modelMap.put("error", "Error al editar perdido. Intentá nuevamente.");
            return new ModelAndView("red-social", modelMap);
        }
    }
}
