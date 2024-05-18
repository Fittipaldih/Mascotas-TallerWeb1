package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;

@Entity
public class PublicacionPerdido extends Publicacion{

    private Integer telefonoContacto;
    private PublicacionTiempo tiempoPublicacion;
    private String direccion; // donde fue encontrado
    private String nombreContacto;

    public PublicacionPerdido(
                    String nombreMascota,
                   String direccion,
                   String nombreContacto,
                   Zona zona,
                   MascotaColor mascotaColor,
                   String descripcion,
                   Integer telefonoContacto) {

        super(nombreMascota ,PublicacionTipo.PERDI_MI_PERRO,zona, mascotaColor, descripcion);
        this.tiempoPublicacion = calcularTiempoPublicacion();
        this.direccion = direccion;
        this.nombreContacto = nombreContacto;
    }

    public PublicacionPerdido() {

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
    public Integer getTelefonoContacto() {return telefonoContacto;}
    public void setTelefonoContacto(Integer telefonoContacto) {this.telefonoContacto = telefonoContacto;}
}
