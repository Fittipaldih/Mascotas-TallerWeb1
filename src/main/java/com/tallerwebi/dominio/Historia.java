package com.tallerwebi.dominio;

public class Historia extends Publicacion{

    private String titular;
    public Historia(TipoPublicacion tipoPublicacion,
                    String titular,
                    Zona zona,
                    String descripcion) {

        super(tipoPublicacion ,zona, descripcion);
        tipoPublicacion = TipoPublicacion.HISTORIAS;
        this.titular=  titular;
    }
    public String getTitular() {
        return titular;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }
}
