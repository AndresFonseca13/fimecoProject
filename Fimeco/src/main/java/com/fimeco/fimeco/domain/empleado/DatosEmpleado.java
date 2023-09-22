package com.fimeco.fimeco.domain.empleado;

import com.fimeco.fimeco.domain.empleado.Empleado;
import com.fimeco.fimeco.domain.empleado.Rol;

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
                            Rol rol,
                            LocalDate fechaIngreso,
                            Integer tiempoServicio,
                            String usuario
                            ) {
    public DatosEmpleado(Empleado empleado){
        this(empleado.getId(), empleado.getNombre(), empleado.getApellido(), empleado.getDocumento(), empleado.getTelefono(), empleado.getTelefono_emergencia(),
                empleado.getEmail(), empleado.getFechaNacimiento(), empleado.getEdad(), empleado.getRol(), empleado.getFechaIngreso(), empleado.getTiempoServicio(), empleado.getUsuario());
    }
}
