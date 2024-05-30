package com.tallerwebi.dominio;

import javax.persistence.Entity;

@Entity
public class PublicacionDonacion extends Publicacion{

    private Double recaudacion;
    private Double montoACubrir;
    public PublicacionDonacion(
                    Double montoACubrir,
                    PublicacionTipo publicacionTipo,
                    String nombreMascota,
                    Zona zona,
                    String descripcion,
                    byte[] imagen) {

        super(publicacionTipo, zona,nombreMascota, descripcion, imagen);
        this.recaudacion = 0.0;
        this.montoACubrir = montoACubrir;
    }

    public PublicacionDonacion() {
        
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

    public void setmontoACubrir(Double montoACubrir) {
        this.montoACubrir = montoACubrir;
    }
}
