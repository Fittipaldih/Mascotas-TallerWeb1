package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.PerdidoException;
import com.tallerwebi.dominio.servicios.ServicioPublicarPerdidoImp;
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

    @Autowired
    private ServicioPublicarPerdidoImp servicioPublicarPerdidoImp;

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
                                        ) throws PerdidoException {
        ModelMap modelMap = new ModelMap();
        try {
            byte[] imagenBytes = null;
            if(imagen != null && !imagen.isEmpty()){
                imagenBytes = imagen.getBytes();
            }
            PublicacionPerdido perdido = new PublicacionPerdido(nombreMascota, direccion, nombreContacto, zona, mascotaColor, descripcion, telefonoContacto, tipoPublicacionPerdido, mascotaRaza, imagenBytes);
            servicioPublicarPerdidoImp.publicarPerdido(perdido, imagen);
            modelMap.put("mensaje", "¡La publicación ha sido creada exitosamente!");
            return new ModelAndView("publicar", modelMap);
        } catch (Exception e) {
            modelMap.put("error", "Error al publicar la mascota perdida. Intentá nuevamente. ");
            return new ModelAndView("publicar", modelMap);
        }
    }

}