package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.List;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota, idUsuario;
    private String nombre, foto, descripcion, latitud, longitud;
    private Integer tiempoBuscado, idColor;

    // Muchas Mascotas pueden tener el mismo Tipo, Raza, Y Estado
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTipoMascota")
    private TipoMascota tipoMascota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRaza")
    private Raza raza;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEstado")
    private Estado estado;

    //Una mascota puede tener muchos comentarios
    @OneToMany(mappedBy = "mascota")
    private List<Comentario> comentarios;

    //Muchas mascotas pueden tener muchas vacunas
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vacunas_mascota",
            joinColumns = {@JoinColumn(name = "idMascota")},
            inverseJoinColumns = {@JoinColumn(name = "idVacuna")} )
    private List<Vacuna> vacunas;

    public Long getIdMascota() {
        return idMascota;
    }
    public Long getIdUsuario() {
        return idUsuario;
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
    public Integer getIdColor() {
        return idColor;
    }
    public List<Comentario> getComentarios() {
        return comentarios;
    }
    public List<Vacuna> getVacunas() {
        return vacunas;
    }
    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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
    public void setIdColor(Integer idColor) {
        this.idColor = idColor;
    }
    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    public void setVacunas(List<Vacuna> vacunas) {
        this.vacunas = vacunas;
    }
}
