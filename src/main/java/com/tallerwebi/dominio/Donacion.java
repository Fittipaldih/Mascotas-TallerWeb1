package com.tallerwebi.dominio;

public class Donacion extends Publicacion{

    private Double recaudacion;
    private Double montoACubrir;
    private String nombre;
    public Donacion(
                    Double montoACubrir,
                    String nombre,
                    Zona zona,
                    String descripcion,
                    Integer telefonoContacto) {

        super(TipoPublicacion.DONACIONES, zona, descripcion, telefonoContacto);
        this.recaudacion = 0.0;
        this.montoACubrir = montoACubrir;
        this.nombre = nombre;
    }

    public Double getRecaudacion() {
        return recaudacion;
    }
    public void setRecaudacion(Double recaudacion) {
        this.recaudacion = recaudacion;
    }
    public void hacerRecaudacion(double importe) {
        this.recaudacion += importe;
    }
    public Double getMontoACubrir() {
        return montoACubrir;
    }
    public void setMontoACubrir(Double montoACubrir) {
        this.montoACubrir = montoACubrir;
    }
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
}
