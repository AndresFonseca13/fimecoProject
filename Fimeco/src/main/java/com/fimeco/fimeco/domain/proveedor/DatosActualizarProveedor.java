package com.fimeco.fimeco.domain.proveedor;

import com.fimeco.fimeco.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarProveedor(@NotNull
                                       Long id,
                                       String nombre,
                                       String telefono,
                                       String email,
                                       DatosDireccion direccion) {

}

