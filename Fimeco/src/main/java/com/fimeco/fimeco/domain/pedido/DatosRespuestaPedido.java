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
}
