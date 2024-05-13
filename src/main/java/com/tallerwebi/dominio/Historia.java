package com.tallerwebi.dominio;

public class Historia extends Publicacion{

    private String titular;
    private Integer telefonoContacto;
    public Historia(
                    String titular,
                    Zona zona,
                    String descripcion,
                    Integer telefonoContacto) {

        super(TipoPublicacion.HISTORIA, zona, descripcion, telefonoContacto);
        this.titular=  titular;
    }
    public String getTitular() {
        return titular;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }
}
