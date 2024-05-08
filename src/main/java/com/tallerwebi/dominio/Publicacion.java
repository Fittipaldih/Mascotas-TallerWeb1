package com.tallerwebi.dominio;

public class Publicacion {

    private TipoPublicacion tipoPublicacion;
    private Zona zona;
    private TiempoBusqueda tiempoBusqueda;
    private ColorPelo colorPelo;
    //deberia tener la clase Perro ?
    private String descripcion;

    public Publicacion(TipoPublicacion tipoPublicacion, Zona zona, TiempoBusqueda tiempoBusqueda, ColorPelo colorPelo, String descripcion) {
        this.tipoPublicacion = tipoPublicacion;
        this.zona = zona;
        this.tiempoBusqueda = tiempoBusqueda;
        this.colorPelo = colorPelo;
        this.descripcion = descripcion;
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

    public TiempoBusqueda getTiempoBusqueda() {
        return tiempoBusqueda;
    }

    public void setTiempoBusqueda(TiempoBusqueda tiempoBusqueda) {
        this.tiempoBusqueda = tiempoBusqueda;
    }

    public ColorPelo getColorPelo() {
        return colorPelo;
    }

    public void setColorPelo(ColorPelo colorPelo) {
        this.colorPelo = colorPelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
