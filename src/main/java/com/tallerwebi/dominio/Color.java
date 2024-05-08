package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// No es Enum porque necesita logica adicional: filtrado de datos por ej
@Entity
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idColor;
    private String nombreColor;

    public Long getIdColor() {
        return idColor;
    }
    public String getNombreColor() {
        return nombreColor;
    }

}
