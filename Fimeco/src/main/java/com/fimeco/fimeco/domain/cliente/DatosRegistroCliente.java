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
                                   @NotNull
                                   String usuario,
                                   @NotNull
                                   @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{6,}$", message = "La clave debe ser de al menos 6 caracteres y debe contener al menos una mayuscula, una minuscula y un numero")
                                   String clave,

                                   String nombrePersona,

                                   @Pattern(regexp = "^[0-9]{1,15}$")
                                   String telefonoPersona,
                                   @Valid
                                   DatosDireccion direccion) {
}
