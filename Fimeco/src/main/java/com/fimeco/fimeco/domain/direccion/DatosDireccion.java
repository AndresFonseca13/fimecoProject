package com.fimeco.fimeco.domain.direccion;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccion(

        String calle,

        String carrera,

        String ciudad,

        String departamento,

        String numero,

        String complemento,

        Pais pais,
        @NotBlank
        String direccionCompleta) {
}
