package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public void setNombreTipoMascota(String nombre) {
        this.nombreTipoMascota = nombre;
    }
}
