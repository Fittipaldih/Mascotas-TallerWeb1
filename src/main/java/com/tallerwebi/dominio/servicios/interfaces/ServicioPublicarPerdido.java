package com.tallerwebi.dominio.servicios.interfaces;

import com.tallerwebi.dominio.PublicacionPerdido;
import com.tallerwebi.dominio.excepcion.PerdidoException;
import org.springframework.web.multipart.MultipartFile;

public interface ServicioPublicarPerdido {
     void publicarPerdido(PublicacionPerdido perdido, MultipartFile imagen) throws PerdidoException;
}
