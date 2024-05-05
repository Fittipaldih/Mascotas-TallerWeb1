package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// No es Enum porque necesita logica adicional: filtrado de datos por ej
@Entity
public class Raza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRaza;

    private String nombreRaza;

    public Long getIdRaza() {
        return idRaza;
    }

    public String getNombreRaza() {
        return nombreRaza;
    }

    public void setIdRaza(Long id) {
        this.idRaza = id;
    }

    public void setNombreRaza(String nombre) {
        this.nombreRaza = nombre;
    }
}
