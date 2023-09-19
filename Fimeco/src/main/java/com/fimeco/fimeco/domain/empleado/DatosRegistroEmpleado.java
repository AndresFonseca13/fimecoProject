package com.fimeco.fimeco.domain.empleado;

public record DatosRegistroEmpleado(
    String documento,
    String nombre,
    String apellido,
    String fechaNacimiento,
    String telefono,
    String telefonoEmergencia,
    String email,
    String rol,
    String fechaIngreso,
    String usuario,
    String clave) {
}
