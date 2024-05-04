package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// No es Enum porque necesita logica adicional: filtrado de datos por ej
@Entity
public class TipoMascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoMascota;

    private String nombreTipoMascota;

    public Long getIdTipoMascota() {
        return idTipoMascota;
    }

    public String getNombreTipoMascota() {
        return nombreTipoMascota;
    }

    public void setIdTipoMascota(Long id) {
        this.idTipoMascota = id;
    }

    public void getNombreTipoMascota(String nombre) {
        this.nombreTipoMascota = nombre;
    }
}
