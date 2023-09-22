package com.fimeco.fimeco.domain.producto;

import jakarta.validation.constraints.NotBlank;

public record DatosRespuestaProducto(Long id,
                                     String nombre,
                                     String descripcion,
                                     Double precio,
                                     @NotBlank
                                     Estado estado,
                                     UnidadMedida unidadMedida,
                                     String tiempoConstruccion,
                                     String tipoTanque,
                                     Long pedido_id) {

    public DatosRespuestaProducto(Producto producto){
        this(producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getEstado(),
                producto.getUnidadMedida(),
                producto.getTiempoConstruccion(),
                producto.getTipoTanque(),
                producto.getPedido().getId());
    }
}
