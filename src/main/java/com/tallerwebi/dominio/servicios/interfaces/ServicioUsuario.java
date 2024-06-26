package com.tallerwebi.dominio.servicios.interfaces;

import com.tallerwebi.dominio.Usuario;

public interface ServicioUsuario {

    Usuario loginUsuario(String email, String password);

    void registrarUsuario(String email, String password, String nombre);

    Boolean validarEmail(String email);

    Usuario getUsuario(Long id);


}

