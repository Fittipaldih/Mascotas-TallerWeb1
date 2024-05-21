package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.PublicacionDonacion;
import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.excepcion.DonacionException;
import com.tallerwebi.dominio.excepcion.HistoriaException;
import com.tallerwebi.dominio.servicios.ServicioPublicarDonacionImp;
import com.tallerwebi.dominio.servicios.ServicioPublicarHistoriaImp;
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
public class ControladorPublicarDonacion {

    @Autowired
    private ServicioPublicarDonacionImp servicioPublicarDonacionImp;

    @RequestMapping(value = "/nueva-donacion", method = RequestMethod.POST)
    public ModelAndView publicarDonacion(@RequestParam(value = "nombreMascota") String nombreMascota,
                                         @RequestParam(value = "monto") Double monto,
                                         @RequestParam(value = "tipoPublicacionDonacion") PublicacionTipo tipoPublicacionDonacion,
                                        @RequestParam(value = "zona") Zona zona,
                                        @RequestParam(value = "descripcion") String descripcion
    ) throws DonacionException {
        ModelMap modelMap = new ModelMap();
        try {
            PublicacionDonacion donacion = new PublicacionDonacion(monto,tipoPublicacionDonacion,nombreMascota,zona,descripcion);

            servicioPublicarDonacionImp.publicarDonacion(donacion);
            modelMap.put("mensaje", "¡La publicación ha sido creada exitosamente!");
            return new ModelAndView("publicar", modelMap);
        } catch (DonacionException e) {
            modelMap.put("error", "Error al publicar la mascota perdida. Intentá nuevamente. ");
            return new ModelAndView("publicar", modelMap);
        }
    }

}