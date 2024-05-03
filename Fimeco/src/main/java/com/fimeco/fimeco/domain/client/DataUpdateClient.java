package com.fimeco.fimeco.domain.client;

import com.fimeco.fimeco.domain.address.DataAddress;
import jakarta.validation.constraints.Pattern;

public record DataUpdateClient(
        Long id,
        String name,
        @Pattern(regexp = "^[0-9]{1,15}$")
        String phone,
        String namePerson,
        @Pattern(regexp = "^[0-9]{1,15}$")
        String phonePerson,
        DataAddress address) {
}
