package com.tallerwebi.dominio;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicacion;

    @Enumerated(EnumType.STRING)
    private PublicacionTipo tipoPublicacion;

    private String fechaPublicacion, descripcion; // se debe agregar la logica para que todas las publicaciones tengan su fecha// ;

    @Enumerated(EnumType.STRING)
    private Zona zona;

    private Integer numContacto;

    @Enumerated(EnumType.STRING)
    private MascotaColor colorPelo;

    // Perdido
    public Publicacion(PublicacionTipo tipoPublicacion, Zona zona, MascotaColor colorPelo, String descripcion, Integer telefonoContacto) {
        this.tipoPublicacion = tipoPublicacion;
        this.zona = zona;
        this.colorPelo = colorPelo;
        this.descripcion = descripcion;
        this.numContacto = telefonoContacto;
        // this.usuario=usuario; FALTAN RECIBIR LOS DATOS DEL USUARIO CONEL ID ES SUFICIENTE YA QUE DE AHI OBTENGO EL RESTO
    }

    // Historia y Donacion
    public Publicacion(PublicacionTipo tipoPublicacion, Zona zona, String descripcion, Integer telefonoContacto) {
        this.zona = zona;
        this.descripcion = descripcion;
        this.numContacto = telefonoContacto;
    }

    public Publicacion() {
    }


    /*
private String emailContacto;

private String nombreUsuario;

private Long idMascota;

public Publicacion(TipoPublicacion tipo, Zona zona, Usuario usuario, Long idMascota) {
    this.tipoPublicacion = tipo;
    this.zona = zona;
    this.fechaPublicacion = getFechaActual();
    this.numContacto = usuario.getTelefono();
    this.emailContacto = usuario.getEmail();
    this.nombreUsuario = usuario.getNombre();
    this.idMascota = idMascota;
}
*/
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

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public Integer getNumContacto() {
        return numContacto;
    }

    public void setNumContacto(Integer numContacto) {
        this.numContacto = numContacto;
    }
/*
    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    */

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public MascotaColor getColorPelo() {
        return colorPelo;
    }

    public void setColorPelo(MascotaColor colorPelo) {
        this.colorPelo = colorPelo;
    }
}
