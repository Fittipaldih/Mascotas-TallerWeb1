package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.MascotaColor;
import com.tallerwebi.dominio.MascotaRaza;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.excepcion.PublicacionInexistenteExeption;

public interface ServicioEditar {

    public void editarHistoria(Long idPublicacion, String titular, String nombreMascota, Zona zona, String descripcion,byte[] imagenBytes) throws PublicacionInexistenteExeption;
    public void editarPerdido(Long idPublicacion, String nombreMascota, Long telefonoContacto, String nombreContacto, MascotaColor mascotaColor, MascotaRaza mascotaRaza,PublicacionTipo tipoPublicacion,Zona zona,String descripcion,String direccion,byte[] imagen) throws PublicacionInexistenteExeption;
    public void editarDonacion(Long idPublicacion, String nombreMascota,Double montoACubrir, Zona zona,String descripcion,byte[] imagen) throws PublicacionInexistenteExeption;

}
