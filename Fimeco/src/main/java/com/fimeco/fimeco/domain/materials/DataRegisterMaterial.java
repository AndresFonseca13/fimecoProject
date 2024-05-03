package com.fimeco.fimeco.domain.materials;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterMaterial(
                                    @NotBlank
                                    String nombre,
                                    @NotBlank
                                    String descripcion,
                                    Integer cantidad,
                                    @NotNull
                                    UnidadMedida unidadMedida,
                                    @NotNull
                                    Double precioUnitario,
                                    @NotNull
                                    State state) {


}
