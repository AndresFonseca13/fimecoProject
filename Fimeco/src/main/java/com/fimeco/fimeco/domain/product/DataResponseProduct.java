package com.fimeco.fimeco.domain.product;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record DataResponseProduct(UUID id,
                                  String name,
                                  String description,
                                  Double price,
                                  @NotBlank
                                     State state,
                                  UnidadMedida unitMeasurement,
                                  String timeConstruction,
                                  String tankType,
                                  UUID order_id) {

    public DataResponseProduct(Product product){
        this(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getState(),
                product.getUnitMeasurement(),
                product.getTimeConstruction(),
                product.getTankType(),
                product.getOrder().getId());
    }
}
