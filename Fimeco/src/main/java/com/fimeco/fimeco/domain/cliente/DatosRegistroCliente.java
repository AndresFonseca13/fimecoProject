package com.fimeco.fimeco.domain.cliente;

import com.fimeco.fimeco.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroCliente(@NotNull
                                   String nombre,
                                   @NotNull
                                   @Pattern(regexp = "^[0-9]{1,15}$", message = "El telefono no cumple con la estructura adecuada")
                                   String telefono,
                                   @NotNull
                                   @Email(message = "El correo no cumple con la estructura adecuada")
                                   String email,

                                   String nombrePersona,

                                   @Pattern(regexp = "^[0-9]{1,15}$")
                                   String telefonoPersona,
                                   @Valid
                                   DatosDireccion direccion,

                                   @NotNull
                                   Integer user_id) {
}
