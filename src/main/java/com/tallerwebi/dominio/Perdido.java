package com.tallerwebi.dominio;

import java.time.LocalDate;

public class Perdido extends Publicacion{
    private TiempoPublicacion tiempoPublicacion;
    private String direccion; // donde fue encontrado
    private String nombreContacto;
    private Integer numeroContacto;

    public Perdido(TipoPublicacion tipoPublicacion,
                   String direccion,
                   String nombreContacto,
                   Integer numeroContacto,
                   Zona zona,
                   String colorPelo,
                   String descripcion) {

        super(tipoPublicacion, zona, colorPelo, descripcion);
        this.tiempoPublicacion = calcularTiempoPublicacion();
        this.direccion = direccion;
        this.nombreContacto = nombreContacto;
        this.numeroContacto = numeroContacto;
    }

    private TiempoPublicacion calcularTiempoPublicacion() {
        LocalDate hoy = LocalDate.now();
        return TiempoPublicacion.HORA;
        //logica segun fecha actual
    }

    public TiempoPublicacion getTiempoBusqueda() {
        return this.tiempoPublicacion;
    }
    public void setTiempoBusqueda(TiempoPublicacion tiempoBusqueda) {
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
