package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.List;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota;
    private String nombre, foto, descripcion, latitud, longitud;
    private Integer tiempoBuscado;

    // Muchas Mascotas pueden tener el mismo Tipo, Raza, Y Estado
    @ManyToOne
    private TipoMascota tipoMascota;

    @ManyToOne
    private Color color;

    // Para identificar el dueño de la mascota
    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Raza raza;

    @ManyToOne
    private Estado estado;

    public Long getIdMascota() {
        return idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFoto() {
        return foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public TipoMascota getTipoMascota() {
        return tipoMascota;
    }

    public Raza getRaza() {
        return raza;
    }

    public Estado getEstado() {
        return estado;
    }

    public Integer getTiempoBuscado() {
        return tiempoBuscado;
    }

    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setTipoMascota(TipoMascota tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setTiempoBuscado(Integer tiempoBuscado) {
        this.tiempoBuscado = tiempoBuscado;
    }

}
