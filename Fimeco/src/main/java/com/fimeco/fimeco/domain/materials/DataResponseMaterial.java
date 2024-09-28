package com.fimeco.fimeco.domain.materials;


import java.util.UUID;

public record DataResponseMaterial(UUID id,
                                   String nombre,
                                   String descripcion,
                                   UnidadMedida unidadMedida,
                                   Double precioUnitario) {

    public DataResponseMaterial(Material material) {
        this(material.getId(),
                material.getNombre(),
                material.getDescripcion(),
                material.getUnidadMedida(),
                material.getPreciounitario());
    }
}

