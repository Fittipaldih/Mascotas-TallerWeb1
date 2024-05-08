package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Peluqueria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeluqueria;
    private Integer telefono;
    private String nombre, direccion;

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Long getIdPeluqueria() {
        return idPeluqueria;
    }

    public void setId(Long idPeluqueria) {
        this.idPeluqueria = idPeluqueria;
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

}
