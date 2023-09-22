package com.fimeco.fimeco.domain.empleado;

public record DatosActualizarEmpleado(Long id,
                                      String nombre,
                                      String apellido,
                                      String telefono,
                                      String telefonoEmergencia,
                                      String email,
                                      Rol rol) {
}
