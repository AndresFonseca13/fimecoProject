package com.fimeco.fimeco.domain.empleado;

public record DatosRespuestaEmpleado(Long id, String nombre, String apellido, String telefono, String email, Cargo cargo) {

        public DatosRespuestaEmpleado(Empleado empleado) {
            this(empleado.getId(), empleado.getNombre(), empleado.getApellido(), empleado.getTelefono(), empleado.getEmail(), empleado.getCargo());
        }
}
