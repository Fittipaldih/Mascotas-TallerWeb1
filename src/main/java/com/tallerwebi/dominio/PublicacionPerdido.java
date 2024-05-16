package com.tallerwebi.dominio;

import java.time.LocalDate;

public class PublicacionPerdido extends Publicacion{

    private Integer telefonoContacto;
    private PublicacionTiempo tiempoPublicacion;
    private String direccion; // donde fue encontrado
    private String nombreContacto;
    private Integer numeroContacto;

    public PublicacionPerdido(
                   String direccion,
                   String nombreContacto,
                   Integer numeroContacto,
                   Zona zona,
                   MascotaColor colorPelo,
                   String descripcion,
                   Integer telefonoContacto) {

        super(PublicacionTipo.PERDI_MI_PERRO,zona, colorPelo, descripcion, telefonoContacto);
        this.tiempoPublicacion = calcularTiempoPublicacion();
        this.direccion = direccion;
        this.nombreContacto = nombreContacto;
        this.numeroContacto = numeroContacto;
    }

    private PublicacionTiempo calcularTiempoPublicacion() {
        LocalDate hoy = LocalDate.now();
        return PublicacionTiempo.DIA;
        //logica segun fecha actual
    }

    public PublicacionTiempo getTiempoBusqueda() {
        return this.tiempoPublicacion;
    }
    public void setTiempoBusqueda(PublicacionTiempo tiempoBusqueda) {
        this.tiempoPublicacion = tiempoBusqueda;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getNombreContacto() {
        return nombreContacto;
    }
    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }
    public Integer getNumeroContacto() {
        return numeroContacto;
    }
    public void setNumeroContacto(Integer numeroContacto) {
        this.numeroContacto = numeroContacto;
    }


}
