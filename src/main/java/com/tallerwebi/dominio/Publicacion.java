package com.tallerwebi.dominio;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public abstract class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicacion;

    @Enumerated(EnumType.STRING)
    private PublicacionTipo tipoPublicacion;

    private String fechaPublicacion, descripcion, nombreMascota;

    @Enumerated(EnumType.STRING)
    private Zona zona;

    @Enumerated(EnumType.STRING)
    private MascotaColor mascotaColor;

    @Enumerated(EnumType.STRING)
    private MascotaRaza mascotaRaza;

    @Lob
    private byte[] imagen;

    // Perdido
    public Publicacion( String nombreMascota, PublicacionTipo tipoPublicacion, Zona zona, MascotaColor colorPelo, String descripcion, MascotaRaza mascotaRaza, byte[] imagen) {
        this.tipoPublicacion = tipoPublicacion;
        this.zona = zona;
        this.mascotaColor = colorPelo;
        this.descripcion = descripcion;
        this.nombreMascota = nombreMascota;
        this.mascotaRaza = mascotaRaza;
        this.imagen = imagen;
        this.fechaPublicacion = getFechaActual();
    }

    // Historia y Donacion
    public Publicacion(PublicacionTipo tipoPublicacion, Zona zona,String nombreMascota, String descripcion, byte[] imagen) {
        this.zona = zona;
        this.descripcion = descripcion;
        this.nombreMascota=nombreMascota;
        this.imagen = imagen;
        this.fechaPublicacion = getFechaActual();
    }

    public Publicacion() {
    }

    public static String getFechaActual() {
        long timestamp = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = dateFormat.format(new Date(timestamp));

        return formattedDate;
    }

    public Long getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Long idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public PublicacionTipo getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(PublicacionTipo tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public MascotaColor getMascotaColor() {
        return mascotaColor;
    }

    public void setMascotaColor(MascotaColor mascotaColor) {
        this.mascotaColor = mascotaColor;
    }

    public MascotaRaza getMascotaRaza() {
        return mascotaRaza;
    }

    public void setMascotaRaza(MascotaRaza mascotaRaza) {
        this.mascotaRaza = mascotaRaza;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }


}
