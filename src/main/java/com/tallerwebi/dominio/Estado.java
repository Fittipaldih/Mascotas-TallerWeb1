package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// No es Enum porque necesita logica adicional: filtrado de datos por ej
@Entity
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstado;

    private String nombreEstado;

    public Long getIdEstado() {
        return idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setIdEstado(Long id) {
        this.idEstado = id;
    }

    public void setNombreEstado(String nombre) {
        this.nombreEstado = nombre;
    }
}
