package com.fimeco.fimeco.domain.materiales;

public record DatosActualizarMaterial(Long id,
                                        Integer cantidad,
                                        Double precioUnitario,
                                        Estado estado) {
}
