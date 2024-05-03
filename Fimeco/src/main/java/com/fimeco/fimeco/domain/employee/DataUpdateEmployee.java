package com.fimeco.fimeco.domain.employee;

public record DataUpdateEmployee(Long id,
                                 String nombre,
                                 String apellido,
                                 String telefono,
                                 String telefonoEmergencia,
                                 String email,
                                 Position position) {
}
