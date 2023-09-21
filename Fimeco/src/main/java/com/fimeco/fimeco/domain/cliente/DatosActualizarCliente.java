package com.fimeco.fimeco.domain.cliente;

import com.fimeco.fimeco.domain.direccion.DatosDireccion;

public record DatosActualizarCliente(
        Long id,
        String nombre,
        String telefono,
        String nombrePersona,
        String telefonoPersona,
        DatosDireccion direccion) {
}
