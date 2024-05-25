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
            Long telefonoContacto,
            PublicacionTipo tipoPublicacionPerdido,
            MascotaRaza mascotaRaza,
            byte[] imagen) {

        super(nombreMascota, tipoPublicacionPerdido, zona, mascotaColor, descripcion, mascotaRaza, imagen);
        this.direccion = direccion;
        this.nombreContacto = nombreContacto;
        this.telefonoContacto = telefonoContacto;

    }

    public PublicacionPerdido() {

    }

    public Long getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(Long telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
