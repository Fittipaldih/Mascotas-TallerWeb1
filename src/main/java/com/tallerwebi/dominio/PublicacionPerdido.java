package com.tallerwebi.dominio;

import javax.persistence.Entity;

@Entity
public class PublicacionPerdido extends Publicacion{

    private Long telefonoContacto;
    private String direccion;
    private String nombreContacto;

    public PublicacionPerdido(
            String nombreMascota,
            String direccion,
            String nombreContacto,
            Zona zona,
            MascotaColor mascotaColor,
            String descripcion,
            Long telefonoContacto) {

        super(nombreMascota, PublicacionTipo.BUSCADO_POR_DUENIO, zona, mascotaColor, descripcion);
        this.direccion = direccion;
        this.nombreContacto = nombreContacto;
        this.telefonoContacto = telefonoContacto;
    }

    public PublicacionPerdido() {

    }

}
