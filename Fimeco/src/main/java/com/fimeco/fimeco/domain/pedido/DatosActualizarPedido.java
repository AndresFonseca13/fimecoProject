package com.fimeco.fimeco.domain.pedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosActualizarPedido(@NotNull
                                    Long id,
                                    LocalDate fechaEntrega,
                                    Estado estado,
                                    String descripcion,
                                    Integer cantidad,
                                    Double precio,
                                    FormaPago formaPago) {
}
