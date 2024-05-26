package com.tallerwebi.dominio;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.Entity;

@Entity
public class PublicacionHistoria extends Publicacion{

    private String titular;
    public PublicacionHistoria(
                    String titular,
                    String nombreMascota,
                    Zona zona,
                    String descripcion,
                    PublicacionTipo publicacionTipo,
                    byte[] imagen) {

        super(publicacionTipo, zona,nombreMascota, descripcion, imagen);
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

    @Override
    public String toString() {
        return "PublicacionHistoria{" +
                "titular='" + titular + '\'' +
                '}';
    }
}
