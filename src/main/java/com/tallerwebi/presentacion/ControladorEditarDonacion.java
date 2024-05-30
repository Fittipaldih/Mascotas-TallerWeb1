package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionDTO;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.servicios.ServicioEditarDonacionImp;
import com.tallerwebi.dominio.servicios.ServicioEditarHistoriaImp;
import com.tallerwebi.dominio.servicios.ServicioPublicacionConversion;
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
public class ControladorEditarDonacion {

    @Autowired
    private ServicioEditarDonacionImp servicioEditarDonacionImp;

    @Autowired
    public ControladorEditarDonacion(ServicioEditarDonacionImp servicioEditarDonacionImp) {
        this.servicioEditarDonacionImp = servicioEditarDonacionImp;
    }



    @Autowired
    private ServicioEditarDonacionImp servicioEditarDonacionImpImp;
    @Autowired
    ServicioRedSocialImpl servicioRedSocial;
    @Autowired
    ServicioPublicacionConversion publicacionConversionService;

    @RequestMapping(value = "/editar-donacion", method = RequestMethod.POST)
    public ModelAndView editarDonacion(  @RequestParam(value = "nombreMascota") String nombreMascota,
                                         @RequestParam(value = "idPublicacion") Long idPublicacion,
                                         @RequestParam(value = "montoACubrir") Double montoACubrir,
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
            servicioEditarDonacionImp.editarDonacion(idPublicacion, nombreMascota,montoACubrir, zona, descripcion, imagenBytes);

            List<Publicacion> todasLasPublicaciones = servicioRedSocial.getTodasLasPublicaciones();
            Collections.reverse(todasLasPublicaciones);
            List<PublicacionDTO> todasLasPublicacionesDTO = publicacionConversionService.convertirEntidadesADTOs(todasLasPublicaciones);
            modelMap.put("todasLasPublicaciones", todasLasPublicacionesDTO);
            modelMap.put("mensaje", "¡La donación ha sido editada exitosamente!");
            return new ModelAndView("red-social", modelMap);
        } catch (Exception e) {
            modelMap.put("error", "Error al editar la donación. Intentá nuevamente.");
            return new ModelAndView("red-social", modelMap);
        }
    }
}
