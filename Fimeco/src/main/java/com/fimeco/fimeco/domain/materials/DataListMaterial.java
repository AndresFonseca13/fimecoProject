package com.fimeco.fimeco.domain.materials;

import java.util.UUID;

public record DataListMaterial(UUID id,
                               String nombre,
                               String descripcion,
                               UnidadMedida unidadMedida,
                               Double precioUnitario,
                               Integer cantidad,
                               State state){
    public DataListMaterial(Material material) {
        this(material.getId(),
                material.getNombre(),
                material.getDescripcion(),
                material.getUnidadMedida(),
                material.getPreciounitario(),
                material.getCantidad(),
                material.getState());
    }

}
