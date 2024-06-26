package com.tallerwebi.dominio.servicios.interfaces;

import javax.servlet.http.HttpServletRequest;

public interface ServicioSession {

    Long getUserId(HttpServletRequest request);

    void setUserId(Long id, HttpServletRequest request);


}
