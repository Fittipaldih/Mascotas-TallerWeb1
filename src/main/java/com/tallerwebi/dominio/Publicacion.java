package com.tallerwebi.dominio;

import javax.persistence.*;

public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPublicacion;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoPublicacion")
    private TipoPublicacion tipoPublicacion;
    @Enumerated(EnumType.STRING)
    @Column(name = "tiempoPublicacion")
    private TiempoPublicacion tiempoPublicacion;
    @Enumerated(EnumType.STRING)
    @Column(name = "zona")
    private Zona zona;
    @ManyToOne
    private Color colorPelo;
    private String descripcion;
    private Integer telefonoContacto;

    private Usuario usuario;

    // Perdido
    public Publicacion(TipoPublicacion tipoPublicacion, Zona zona, Color colorPelo, String descripcion, Integer telefonoContacto) {
        this.tipoPublicacion = tipoPublicacion;
        this.tiempoPublicacion = null ;// aca debe ir la logica para que todas tengan la FECHA de publicacion
        this.zona = zona;
        this.colorPelo = colorPelo;
        this.descripcion = descripcion;
        this.telefonoContacto = telefonoContacto;
        // this.usuario=usuario; FALTAN RECIBIR LOS DATOS DEL USUARIO CONEL ID ES SUFICIENTE YA QUE DE AHI OBTENGO EL RESTO
    }

    // Historia y Donacion
    public Publicacion(TipoPublicacion tipoPublicacion, Zona zona, String descripcion, Integer telefonoContacto) {
        this.tiempoPublicacion = tiempoPublicacion;
        this.zona = zona;
        this.descripcion = descripcion;
        this.telefonoContacto = telefonoContacto;;
    }

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Integer getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(Integer telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoPublicacion getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public Color getColorPelo() {
        return colorPelo;
    }

    public void setColorPelo(Color colorPelo) {
        this.colorPelo = colorPelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TiempoPublicacion getTiempoPublicacion() {
        return tiempoPublicacion;
    }

    public void setTiempoPublicacion(TiempoPublicacion tiempoPublicacion) {
        this.tiempoPublicacion = tiempoPublicacion;
    }
}
