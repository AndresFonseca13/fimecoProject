package com.fimeco.fimeco.domain.cliente;

import com.fimeco.fimeco.domain.direccion.DatosDireccion;

public record DatosRespuestaCliente(
        Long id,
        String nombre,
        String email,
        String telefono,
        String nombrePersona,
        DatosDireccion direccion
) {
}
