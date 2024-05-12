package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idColor;
    private String nombre;

    public Color(Integer idColor, String nombre) {
        this.idColor = idColor;
        this.nombre = nombre;
    }

    public Color() {

    }

    public Integer getIdColor() {
        return idColor;
    }

    public String getNombre() {
        return nombre;
    }

}
