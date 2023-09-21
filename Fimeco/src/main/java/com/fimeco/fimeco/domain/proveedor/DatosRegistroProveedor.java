package com.fimeco.fimeco.domain.proveedor;

import com.fimeco.fimeco.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroProveedor(@NotNull
                                     String nombre,
                                     @NotNull
                                     @Pattern(regexp = "^[0-9]{1,15}$")
                                     String telefono,
                                     @NotNull
                                     @Email
                                     String email,
                                     @NotNull
                                     Tipo tipo,
                                     @Valid
                                     DatosDireccion direccion) {
}
