package com.fimeco.fimeco.domain.pedido;

import java.time.LocalDate;

public record DatosRespuestaPedido(
        Long id,
        LocalDate fechaPedido,
        LocalDate fechaEntrega,
        Estado estado,
        String descripcion,
        Integer cantidad,
        Double precio,
        FormaPago formaPago,
        Long clienteId,
        String nombreCliente) {

    public DatosRespuestaPedido(Pedido pedido){
        this(pedido.getId(),
                pedido.getFechaPedido(),
                pedido.getFechaEntrega(),
                pedido.getEstado(),
                pedido.getDescripcion(),
                pedido.getCantidad(),
                pedido.getPrecio(),
                pedido.getFormaPago(),
                pedido.getCliente().getId(),
                pedido.getCliente().getNombre());
    }
}
