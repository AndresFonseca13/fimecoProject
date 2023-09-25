package com.fimeco.fimeco.domain.pedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosRegistroPedido(
        @NotNull
        LocalDate fechaEntrega,
        @NotNull
        Estado estado,
        @NotBlank
        String descripcion,

        Integer cantidad,
        @NotNull
        Double precio,
        @NotNull
        FormaPago formaPago,
        @NotNull
        Long clienteId) {
}
