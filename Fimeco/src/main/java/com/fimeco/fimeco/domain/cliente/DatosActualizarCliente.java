package com.fimeco.fimeco.domain.cliente;

import com.fimeco.fimeco.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.Pattern;

public record DatosActualizarCliente(
        Long id,
        String nombre,
        @Pattern(regexp = "^[0-9]{1,15}$")
        String telefono,
        String nombrePersona,
        @Pattern(regexp = "^[0-9]{1,15}$")
        String telefonoPersona,
        DatosDireccion direccion) {
}
