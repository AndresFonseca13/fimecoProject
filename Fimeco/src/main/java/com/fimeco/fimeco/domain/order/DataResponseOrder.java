package com.fimeco.fimeco.domain.order;

import java.time.LocalDate;

public record DataResponseOrder(
        Long id,
        LocalDate fechaPedido,
        LocalDate fechaEntrega,
        State state,
        String descripcion,
        Integer cantidad,
        Double precio,
        PayWay payWay,
        Long clienteId,
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
