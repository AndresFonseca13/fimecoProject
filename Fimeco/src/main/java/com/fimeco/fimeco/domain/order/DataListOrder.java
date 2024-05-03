package com.fimeco.fimeco.domain.order;

import java.time.LocalDate;

public record DataListOrder(Long id,
                            LocalDate fechaPedido,
                            LocalDate fechaEntrega,
                            State state,
                            String descripcion,
                            Integer cantidad,
                            Long clienteId) {

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
