package com.tallerwebi.dominio;

public class Perdido extends Publicacion{
    private TiempoPublicacion tiempoPublicacion;
    private String direccion; // donde fue encontrado

    public Perdido(TipoPublicacion tipoPublicacion,
                   String direccion,
                   Zona zona,
                   String colorPelo,
                   String descripcion) {

        super(tipoPublicacion, zona, colorPelo, descripcion);
        tipoPublicacion = TipoPublicacion.PERDIDOS;
        this.tiempoPublicacion = calcularTiempoPublicacion();
        this.direccion = direccion;
    }

    private TiempoPublicacion calcularTiempoPublicacion() {
        return TiempoPublicacion.HORA;
        //logica segun fecha actual
    }

    public TiempoPublicacion getTiempoBusqueda() {
        return this.tiempoPublicacion;
    }
    public void setTiempoBusqueda(TiempoPublicacion tiempoBusqueda) {
        this.tiempoPublicacion = tiempoBusqueda;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
