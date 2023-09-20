package com.fimeco.fimeco.domain.cliente;

import com.fimeco.fimeco.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroCliente(@NotNull
                                   String nombre,
                                   @NotNull
                                   @Pattern(regexp = "^[0-9]{1,15}$")
                                   String telefono,
                                   @NotNull
                                   @Email
                                   String email,
                                   @NotNull
                                   String usuario,
                                   @NotNull
                                   @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{6,}$")
                                   String clave,

                                   String nombrePersona,

                                   @Pattern(regexp = "^[0-9]{1,15}$")
                                   String telefonoPersona,
                                   @Valid
                                   DatosDireccion direccion) {
}
