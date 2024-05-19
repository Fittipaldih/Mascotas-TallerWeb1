package com.tallerwebi.dominio;

import javax.persistence.*;

@Entity
public class Veterinaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVeterinaria;
    private Long telefono;
    private String nombre, direccion;
    @Enumerated(EnumType.STRING)
    private Zona zona;
    private Boolean open24;

    public Veterinaria() {

    }

    public Veterinaria(Long telefono, String nombre, String direccion, Zona zona, Boolean open24) {
        this.telefono = telefono;
        this.nombre = nombre;
        this.direccion = direccion;
        this.zona = zona;
        this.open24 = open24;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
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

    public Zona getZona() {
        return zona;
    }
    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
