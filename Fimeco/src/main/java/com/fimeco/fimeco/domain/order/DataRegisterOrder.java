package com.fimeco.fimeco.domain.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DataRegisterOrder(
        @NotNull
        LocalDate fechaEntrega,
        @NotNull
        State state,
        @NotBlank
        String descripcion,

        Integer cantidad,
        @NotNull
        Double precio,
        @NotNull
        PayWay payWay,
        @NotNull
        Long clienteId) {
}
