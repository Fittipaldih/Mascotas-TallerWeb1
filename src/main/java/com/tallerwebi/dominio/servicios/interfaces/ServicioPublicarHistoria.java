package com.tallerwebi.dominio.servicios.interfaces;

import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.excepcion.HistoriaException;
import org.springframework.web.multipart.MultipartFile;

public interface ServicioPublicarHistoria {
    void publicarHistoria(PublicacionHistoria historia, MultipartFile imagen) throws HistoriaException;
}
