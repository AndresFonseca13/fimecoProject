package com.fimeco.fimeco.domain.materiales;



public record DatosRespuestaMaterial(Long id,
                                     String nombre,
                                     String descripcion,
                                     UnidadMedida unidadMedida,
                                     Double precioUnitario) {

    public DatosRespuestaMaterial(Material material) {
        this(material.getId(),
                material.getNombre(),
                material.getDescripcion(),
                material.getUnidadMedida(),
                material.getPreciounitario());
    }
}

