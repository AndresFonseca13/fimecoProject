package com.fimeco.fimeco.domain.producto;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarProducto(Long id,
                                      @NotBlank
                                      String nombre,
                                      String descripcion,
                                      Double precio,
                                      @NotBlank
                                      Estado estado,
                                      String tiempoConstruccion,
                                      String tipoTanque) {
}
