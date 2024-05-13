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
    @OneToMany
    @JoinColumn(name = "idPublicacion")
    private List<Publicacion> publicaciones;
    @Enumerated(EnumType.STRING)
    private TipoMascota tipoMascota;
    @Enumerated(EnumType.STRING)
    private RazaMascota raza;
    @Enumerated(EnumType.STRING)
    private ColorMascota color;
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    private EstadoMascota estado;

    public Mascota(String nombre, String foto, String descripcion, Zona zona, TipoMascota tipoMascota, RazaMascota raza, ColorMascota color, Usuario usuario, EstadoMascota estado) {
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

    public Mascota(String nombre, String foto, String descripcion, Zona zona, TipoMascota tipoMascota, ColorMascota color, Usuario usuario, EstadoMascota estado, Publicacion publicacion) {
        this.nombre = nombre;
        this.foto = foto;
        this.descripcion = descripcion;
        this.zona = zona;
        this.tipoMascota = tipoMascota;
        this.color = color;
        this.usuario = usuario;
        this.estado = estado;
        if(publicacion != null && (!this.getPublicaciones().contains(publicacion))){
            this.publicaciones.add(publicacion);
        }

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

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public TipoMascota getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascota tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public ColorMascota getColor() {
        return color;
    }

    public void setColor(ColorMascota color) {
        this.color = color;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EstadoMascota getEstado() {
        return estado;
    }

    public void setEstado(EstadoMascota estado) {
        this.estado = estado;
    }

    public RazaMascota getRaza() {
        return raza;
    }

    public void setRaza(RazaMascota raza) {
        this.raza = raza;
    }
}
