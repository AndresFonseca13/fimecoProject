package com.fimeco.fimeco.domain.producto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroProducto(
        @NotBlank
        String nombre,
        String descripcion,
        Double precio,
        @NotBlank
        Estado estado,
        UnidadMedida unidadMedida,
        String tiempoConstruccion,
        String tipoTanque,
        @NotNull
        Long pedido_id) {
}
