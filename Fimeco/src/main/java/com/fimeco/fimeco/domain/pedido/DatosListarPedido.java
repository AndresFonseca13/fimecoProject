package com.fimeco.fimeco.domain.pedido;

import java.time.LocalDate;

public record DatosListarPedido(Long id,
                                LocalDate fechaPedido,
                                LocalDate fechaEntrega,
                                Estado estado,
                                String descripcion,
                                Integer cantidad,
                                Long clienteId) {

    public DatosListarPedido(Pedido pedido){
        this(pedido.getId(),
                pedido.getFechaPedido(),
                pedido.getFechaEntrega(),
                pedido.getEstado(),
                pedido.getDescripcion(),
                pedido.getCantidad(),
                pedido.getCliente().getId());
    }

}
