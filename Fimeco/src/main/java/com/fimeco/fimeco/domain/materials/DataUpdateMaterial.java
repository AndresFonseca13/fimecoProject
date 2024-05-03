package com.fimeco.fimeco.domain.materials;

public record DataUpdateMaterial(Long id,
                                 Integer cantidad,
                                 Double precioUnitario,
                                 State state) {
}
