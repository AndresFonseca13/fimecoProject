package com.fimeco.fimeco.domain.order;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DataUpdateOrder(@NotNull
                                    Long id,
                              LocalDate fechaEntrega,
                              State state,
                              String descripcion,
                              Integer cantidad,
                              Double precio,
                              PayWay payWay) {
}
