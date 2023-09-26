package com.fimeco.fimeco.domain.materiales;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroMaterial(
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
                                    Estado estado) {


}
