package com.tallerwebi.dominio;

import javax.persistence.*;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComentario;

    private String contenido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPublicacion")
    private Publicacion publicacion;

    public Comentario(String contenido) {
        this.contenido = contenido;
    }

    public Comentario() {

    }

    public Long getId() {
        return idComentario;
    }

    public void setId(Long id) {
        this.idComentario = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        // editar comentario
        this.contenido = contenido;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }


}
