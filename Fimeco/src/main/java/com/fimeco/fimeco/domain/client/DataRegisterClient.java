package com.fimeco.fimeco.domain.client;

import com.fimeco.fimeco.domain.address.DataAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DataRegisterClient(@NotNull
                                   String name,
                                 @NotNull
                                   @Pattern(regexp = "^[0-9]{1,15}$", message = "El phone no cumple con la estructura adecuada")
                                   String phone,

                                 @NotNull
                                   @Email(message = "El correo no cumple con la estructura adecuada")
                                   String email,

                                 String namePerson,

                                 @Pattern(regexp = "^[0-9]{1,15}$")
                                   String phonePerson,

                                 @Valid
                                 DataAddress address,

                                 @NotNull
                                   Integer user_id) {
}
