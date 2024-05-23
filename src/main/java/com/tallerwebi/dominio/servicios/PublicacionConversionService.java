package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionDTO;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionConversionService {

    public PublicacionDTO convertirEntidadADTO(Publicacion publicacion) {
        PublicacionDTO dto = new PublicacionDTO();
        dto.setIdPublicacion(publicacion.getIdPublicacion());
        dto.setTipoPublicacion(publicacion.getTipoPublicacion().name());
        dto.setFechaPublicacion(publicacion.getFechaPublicacion());
        dto.setDescripcion(publicacion.getDescripcion());
        dto.setNombreMascota(publicacion.getNombreMascota());
        dto.setZona(publicacion.getZona().name());
        dto.setMascotaColor(publicacion.getMascotaColor() != null ? publicacion.getMascotaColor().name() : null);
        dto.setMascotaRaza(publicacion.getMascotaRaza() != null ? publicacion.getMascotaRaza().name() : null);

        if (publicacion.getImagen() != null) {
            dto.setImagen(Base64.getEncoder().encodeToString(publicacion.getImagen()));
        }
        return dto;
    }

    public List<PublicacionDTO> convertirEntidadesADTOs(List<Publicacion> publicaciones) {
        return publicaciones.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }
}