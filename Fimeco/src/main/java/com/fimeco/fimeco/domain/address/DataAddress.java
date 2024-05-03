package com.fimeco.fimeco.domain.address;

import jakarta.validation.constraints.NotBlank;

public record DataAddress(

        String street,

        String race,

        String city,

        String department,

        String number,

        String complement,

        Country country,
        @NotBlank
        String addressComplete) {
}
