package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.List;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre, foto, descripcion, latitud, longitud;
    private Zona zona;
    private Integer tiempoBuscado;
    @Enumerated(EnumType.STRING)
    private MascotaTipo tipoMascota;
    @Enumerated(EnumType.STRING)
    private MascotaRaza raza;
    @Enumerated(EnumType.STRING)
    private MascotaColor color;
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    private MascotaEstado estado;

    public Mascota(String nombre, String foto, String descripcion, Zona zona, MascotaTipo tipoMascota, MascotaRaza raza, MascotaColor color, MascotaEstado estado) {
        this.nombre = nombre;
        this.foto = foto;
        this.descripcion = descripcion;
        this.zona = zona;
        this.tipoMascota = tipoMascota;
        this.raza = raza;
        this.color = color;
        this.estado = estado;
    }

    public Mascota(String nombre, String foto, String descripcion, Zona zona, MascotaTipo tipoMascota, MascotaRaza raza, MascotaColor color, Usuario usuario, MascotaEstado estado) {
        this.nombre = nombre;
        this.foto = foto;
        this.descripcion = descripcion;
        this.zona = zona;
        this.tipoMascota = tipoMascota;
        this.raza = raza;
        this.color = color;
        this.usuario = usuario;
        this.estado = estado;
    }

    public Mascota(String nombre, String foto, String descripcion, Zona zona, MascotaTipo tipoMascota, MascotaColor color, Usuario usuario, MascotaEstado estado, Publicacion publicacion) {
        this.nombre = nombre;
        this.foto = foto;
        this.descripcion = descripcion;
        this.zona = zona;
        this.tipoMascota = tipoMascota;
        this.color = color;
        this.usuario = usuario;
        this.estado = estado;

    }

    public Mascota() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public Integer getTiempoBuscado() {
        return tiempoBuscado;
    }

    public void setTiempoBuscado(Integer tiempoBuscado) {
        this.tiempoBuscado = tiempoBuscado;
    }

    public MascotaTipo getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(MascotaTipo tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public MascotaColor getColor() {
        return color;
    }

    public void setColor(MascotaColor color) {
        this.color = color;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public MascotaEstado getEstado() {
        return estado;
    }

    public void setEstado(MascotaEstado estado) {
        this.estado = estado;
    }

    public MascotaRaza getRaza() {
        return raza;
    }

    public void setRaza(MascotaRaza raza) {
        this.raza = raza;
    }
}
