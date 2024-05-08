package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Veterinaria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVeterinaria;
    private Integer telefono;
    private String nombre, direccion;
    private Boolean open24;

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Long getIdVeterinaria() {
        return idVeterinaria;
    }

    public void setId(Long idVeterinaria) {
        this.idVeterinaria = idVeterinaria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getOpen24() {
        return open24;
    }

    public void setOpen24(Boolean open24) {
        this.open24 = open24;
    }
}
