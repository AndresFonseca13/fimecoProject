package com.fimeco.fimeco.domain.product;

import jakarta.validation.constraints.NotBlank;

public record DataUpdateProduct(Long id,
                                @NotBlank
                                      String name,
                                String description,
                                Double price,
                                @NotBlank
                                      State state,
                                String timeConstruction,
                                String tankType) {
}
