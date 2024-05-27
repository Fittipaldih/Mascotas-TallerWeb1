package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.servicios.ServicioDetallePublicacionImpl;
import com.tallerwebi.dominio.servicios.ServicioPublicacionConversion;
import com.tallerwebi.dominio.servicios.ServicioRedSocialImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@Transactional
public class ControladorDetallePublicacion {

    @Autowired
    private ServicioDetallePublicacionImpl servicioDetallePublicacion;
    @Autowired
    ServicioRedSocialImpl servicioRedSocial;
    @Autowired
    ServicioPublicacionConversion publicacionConversionService;

    @RequestMapping(value = "/detalle-publicacion", method = RequestMethod.GET)
    public ModelAndView mostrarDetallePublicacion(@RequestParam Long id){
        ModelMap model = new ModelMap();
        try {
            Publicacion publicacion = servicioDetallePublicacion.getPublicacion(id);
            Collections.reverse(publicacion.getComentarios());
            model.put("publicacionData", publicacion);

            if (publicacion instanceof PublicacionHistoria) {
                PublicacionHistoria historia = (PublicacionHistoria) publicacion;
                model.put("historia", historia);
            } else if (publicacion instanceof PublicacionDonacion) {
                PublicacionDonacion donacion = (PublicacionDonacion) publicacion;
                model.put("donacion", donacion);
            } else if (publicacion instanceof PublicacionPerdido) {
                PublicacionPerdido perdido = (PublicacionPerdido) publicacion;
                model.put("perdido", perdido);
            }

            return new ModelAndView("detalle-publicacion", model);
        } catch (Exception e) {
            model.put("mensaje", e.getMessage());
            return new ModelAndView("home", model);
        }
    }

    @RequestMapping(value = "/comentarPublicacion", method = RequestMethod.POST)
    public String realizarComentario(@RequestParam(value = "idPublicacion") Long idPublicacion,
                                     @RequestParam(value = "textoDelComentario") String textoDelComentario
    ) {
        try {
            servicioDetallePublicacion.hacerComentario(textoDelComentario, idPublicacion);
            return "redirect:/detalle-publicacion?id=" + idPublicacion;

        } catch (Exception e) {
            return "redirect:/detalle-publicacion?id=" + idPublicacion;
        }
    }
    @RequestMapping(value = "/eliminar-publicacion", method = RequestMethod.POST)
    public ModelAndView eliminarPublicacion( @RequestParam(value = "idPublicacion") Long idPublicacion) {
        ModelMap modelMap = new ModelMap();
        try {

            servicioDetallePublicacion.eliminarPublicacion(idPublicacion);


            List<Publicacion> todasLasPublicaciones = servicioRedSocial.getTodasLasPublicaciones();
            Collections.reverse(todasLasPublicaciones);
            List<PublicacionDTO> todasLasPublicacionesDTO = publicacionConversionService.convertirEntidadesADTOs(todasLasPublicaciones);
            modelMap.put("todasLasPublicaciones", todasLasPublicacionesDTO);
            modelMap.put("mensaje", "¡La publicacion ha sido eliminada con exito!");
            return new ModelAndView("red-social", modelMap);
        } catch (Exception e) {
            modelMap.put("error", "Error al eliminar la publicacion. Intentá nuevamente.");
            return new ModelAndView("red-social", modelMap);
        }
    }
    @RequestMapping(value = "/editar-publicacion", method = RequestMethod.GET)
    public ModelAndView editarPublicacion(@RequestParam(value = "tipoPublicacion") PublicacionTipo publicacionTipo,
                                          @RequestParam(value = "idPublicacion") Long idPublicacion) {
        ModelMap model = new ModelMap();
        String seccionEditar = this.servicioRedSocial.getSeccionEditar(publicacionTipo);
        Publicacion publicacionBuscada =  this.servicioRedSocial.getPublicacionPorId(idPublicacion);

        model.addAttribute("publicacionBuscada", publicacionBuscada);
//        model.put("mensaje", "Debe ser algo asi");

        return new ModelAndView("editar-" + seccionEditar,model);
    }


}

