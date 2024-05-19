package com.tallerwebi.dominio;

import javax.persistence.Entity;

@Entity
public class PublicacionHistoria extends Publicacion{

    private String titular;
    public PublicacionHistoria(
                    String titular,
                    String nombreMascota,
                    Zona zona,
                    String descripcion,
                    PublicacionTipo publicacionTipo) {

        super(publicacionTipo, zona,nombreMascota, descripcion);
        this.titular=  titular;
    }

    public PublicacionHistoria() {

    }

    public String getTitular() {
        return titular;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }
}
