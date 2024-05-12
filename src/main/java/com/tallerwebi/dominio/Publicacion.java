package com.tallerwebi.dominio;

public class Publicacion {

    private TipoPublicacion tipoPublicacion;
    private Zona zona;
    private String colorPelo;
    private String descripcion;

// perdido
    public Publicacion(TipoPublicacion tipoPublicacion, Zona zona, String colorPelo, String descripcion) {
        this.tipoPublicacion = tipoPublicacion;
        this.zona = zona;
        this.colorPelo = colorPelo;
        this.descripcion = descripcion;
    }

// historia y donacion
    public Publicacion(TipoPublicacion tipoPublicacion, Zona zona, String descripcion) {
        this.tipoPublicacion = tipoPublicacion;
        this.zona = zona;
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

    public String getColorPelo() {
        return colorPelo;
    }

    public void setColorPelo(String colorPelo) {
        this.colorPelo = colorPelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
