package com.tallerwebi.dominio;

public class PublicacionHistoria extends Publicacion{

    private String titular;
    private Long telefonoContacto;
    public PublicacionHistoria(
                    String titular,
                    Zona zona,
                    String descripcion,
                    Long telefonoContacto) {

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
