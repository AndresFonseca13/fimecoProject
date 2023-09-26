package com.fimeco.fimeco.domain.materiales;

public record DatosListarMateriales (Long id,
                                     String nombre,
                                     String descripcion,
                                     UnidadMedida unidadMedida,
                                     Double precioUnitario,
                                     Integer cantidad,
                                     Estado estado){
    public DatosListarMateriales(Material material) {
        this(material.getId(),
                material.getNombre(),
                material.getDescripcion(),
                material.getUnidadMedida(),
                material.getPreciounitario(),
                material.getCantidad(),
                material.getEstado());
    }

}
