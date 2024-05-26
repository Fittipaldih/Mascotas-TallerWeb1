package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.servicios.ServicioEditarHistoriaImp;
import com.tallerwebi.dominio.servicios.ServicioPublicacionConversion;
import com.tallerwebi.dominio.servicios.ServicioRedSocial;
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
public class ControladorEditarHistoria {

    @Autowired
    private ServicioEditarHistoriaImp servicioEditarHistoriaImp;
    @Autowired
    ServicioRedSocialImpl servicioRedSocial;
    @Autowired
    ServicioPublicacionConversion publicacionConversionService;

    @RequestMapping(value = "/editar-historia", method = RequestMethod.POST)
    public ModelAndView editarHistoria(@RequestParam(value = "titular") String titular,
                                         @RequestParam(value = "nombre") String nombreMascota,
                                         @RequestParam(value = "idPublicacion") Long idPublicacion,
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
            servicioEditarHistoriaImp.editarHistoria(idPublicacion, titular, nombreMascota, zona, descripcion, imagenBytes);

            List<Publicacion> todasLasPublicaciones = servicioRedSocial.getTodasLasPublicaciones();
            Collections.reverse(todasLasPublicaciones);
            List<PublicacionDTO> todasLasPublicacionesDTO = publicacionConversionService.convertirEntidadesADTOs(todasLasPublicaciones);
            modelMap.put("todasLasPublicaciones", todasLasPublicacionesDTO);
            modelMap.put("mensaje", "¡La historia ha sido editada exitosamente!");
            return new ModelAndView("red-social", modelMap);
        } catch (Exception e) {
            modelMap.put("error", "Error al editar la historia. Intentá nuevamente.");
            return new ModelAndView("red-social", modelMap);
        }
    }
}
