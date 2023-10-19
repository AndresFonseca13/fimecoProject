package com.fimeco.fimeco.domain.empleado;

import java.time.LocalDate;

public record DatosEmpleado(Long id,
                            String nombre,
                            String apellido,
                            String documento,
                            String telefono,
                            String telefonoEmergencia,
                            String email,
                            LocalDate fechaNacimiento,
                            Integer edad,
                            Cargo rol,
                            LocalDate fechaIngreso,
                            Integer tiempoServicio
) {
    public DatosEmpleado(Empleado empleado){
        this(empleado.getId(), empleado.getNombre(), empleado.getApellido(), empleado.getDocumento(), empleado.getTelefono(), empleado.getTelefono_emergencia(),
                empleado.getEmail(), empleado.getFechaNacimiento(), empleado.getEdad(), empleado.getCargo(), empleado.getFechaIngreso(), empleado.getTiempoServicio());
    }
}
