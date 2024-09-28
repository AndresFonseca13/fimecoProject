package com.fimeco.fimeco.domain.order;

import java.time.LocalDate;
import java.util.UUID;

public record DataResponseOrder(
        UUID id,
        LocalDate fechaPedido,
        LocalDate fechaEntrega,
        State state,
        String descripcion,
        Integer cantidad,
        Double precio,
        PayWay payWay,
        UUID clienteId,
        String nombreCliente) {

    public DataResponseOrder(Order order){
        this(order.getId(),
                order.getOrderDate(),
                order.getDeliveryDate(),
                order.getState(),
                order.getDescription(),
                order.getQuantity(),
                order.getPrice(),
                order.getPayWay(),
                order.getClient().getId(),
                order.getClient().getName());
    }
}
