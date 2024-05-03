package com.fimeco.fimeco.domain.product;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterProduct(
        @NotBlank
        String name,
        String description,
        Double price,
        @NotBlank
        State state,
        UnidadMedida unitMeasurement,
        String timeConstruction,
        String tankType,
        @NotNull
        Long order_id) {
}
