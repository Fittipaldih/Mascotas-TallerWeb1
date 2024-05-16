package com.tallerwebi.dominio;

public class PublicacionHistoria extends Publicacion{

    private String titular;
    private Integer telefonoContacto;
    public PublicacionHistoria(
                    String titular,
                    Zona zona,
                    String descripcion,
                    Integer telefonoContacto) {

        super(PublicacionTipo.HISTORIA, zona, descripcion, telefonoContacto);
        this.titular=  titular;
    }
    public String getTitular() {
        return titular;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }
}
