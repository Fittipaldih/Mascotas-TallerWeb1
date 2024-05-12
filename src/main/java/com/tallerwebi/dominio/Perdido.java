package com.tallerwebi.dominio;

import java.time.LocalDate;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Perdido extends Publicacion{

    private Integer telefonoContacto;
    private TiempoPublicacion tiempoPublicacion;
    private String direccion; // donde fue encontrado
    private String nombreContacto;
    private Integer numeroContacto;

    public Perdido(
                   String direccion,
                   String nombreContacto,
                   Integer numeroContacto,
                   Zona zona,
                   Color colorPelo,
                   String descripcion,
                   Integer telefonoContacto) {

        super(TipoPublicacion.PERDIDOS,zona, colorPelo, descripcion, telefonoContacto);
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
