package com.fimeco.fimeco.domain.direccion;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccion(
        @NotBlank
        String calle,
        @NotBlank
        String carrera,
        @NotBlank
        String ciudad,
        @NotBlank
        String departamento,
        @NotBlank
        String numero,
        @NotBlank
        String complemento,
        @NotBlank
        Pais pais,
        @NotBlank
        String direccionCompleta) {
}
