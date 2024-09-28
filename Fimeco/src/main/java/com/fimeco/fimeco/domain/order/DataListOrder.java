package com.fimeco.fimeco.domain.order;

import java.time.LocalDate;
import java.util.UUID;

public record DataListOrder(UUID id,
                            LocalDate fechaPedido,
                            LocalDate fechaEntrega,
                            State state,
                            String descripcion,
                            Integer cantidad,
                            UUID clienteId) {

    public DataListOrder(Order order){
        this(order.getId(),
                order.getOrderDate(),
                order.getDeliveryDate(),
                order.getState(),
                order.getDescription(),
                order.getQuantity(),
                order.getClient().getId());
    }

}
