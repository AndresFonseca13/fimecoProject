package com.fimeco.fimeco.domain.materials;



public record DataResponseMaterial(Long id,
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

