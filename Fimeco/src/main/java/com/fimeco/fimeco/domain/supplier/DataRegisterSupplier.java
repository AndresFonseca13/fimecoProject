package com.fimeco.fimeco.domain.supplier;

import com.fimeco.fimeco.domain.address.DataAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DataRegisterSupplier(@NotNull
                                     String name,
                                   @NotNull
                                     @Pattern(regexp = "^[0-9]{1,15}$")
                                     String phone,
                                   @NotNull
                                     @Email
                                     String email,
                                   @NotNull
                                   Type type,
                                   @Valid
                                     DataAddress address) {
}
