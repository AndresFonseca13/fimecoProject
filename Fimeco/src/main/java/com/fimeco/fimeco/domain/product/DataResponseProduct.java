package com.fimeco.fimeco.domain.product;

import jakarta.validation.constraints.NotBlank;

public record DataResponseProduct(Long id,
                                  String name,
                                  String description,
                                  Double price,
                                  @NotBlank
                                     State state,
                                  UnidadMedida unitMeasurement,
                                  String timeConstruction,
                                  String tankType,
                                  Long order_id) {

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
