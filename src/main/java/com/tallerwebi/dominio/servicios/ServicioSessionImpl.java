package com.tallerwebi.dominio.servicios;

import javax.servlet.http.HttpServletRequest;

import com.tallerwebi.dominio.servicios.interfaces.ServicioSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioSession")
@Transactional
public class ServicioSessionImpl implements ServicioSession {

    @Override
    public Long getUserId(HttpServletRequest request) {
        return (Long)request.getSession().getAttribute("ID");
    }

    @Override
    public void setUserId(Long id,HttpServletRequest request) {
        request.getSession().setAttribute("ID",id);
    }


}
