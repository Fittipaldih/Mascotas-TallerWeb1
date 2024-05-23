package com.tallerwebi.dominio;

public class PublicacionDTO {
    // Data Transfer Object, Necesario Para Las Imagenes
    private Long idPublicacion;
    private String tipoPublicacion;
    private String fechaPublicacion;
    private String descripcion;
    private String nombreMascota;
    private String zona;
    private String mascotaColor;
    private String mascotaRaza;
    private String imagen;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getMascotaRaza() {
        return mascotaRaza;
    }

    public void setMascotaRaza(String mascotaRaza) {
        this.mascotaRaza = mascotaRaza;
    }

    public String getMascotaColor() {
        return mascotaColor;
    }

    public void setMascotaColor(String mascotaColor) {
        this.mascotaColor = mascotaColor;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(String tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public Long getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Long idPublicacion) {
        this.idPublicacion = idPublicacion;
    }


}

