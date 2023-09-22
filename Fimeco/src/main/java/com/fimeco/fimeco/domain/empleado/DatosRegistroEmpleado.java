package com.fimeco.fimeco.domain.empleado;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record DatosRegistroEmpleado(@NotBlank
                                    String documento,
                                    @NotBlank
                                    String nombre,
                                    @NotBlank
                                    String apellido,
                                    @NotNull
                                    LocalDate fechaNacimiento,
                                    @NotBlank
                                    @Pattern(regexp = "^[0-9]{1,15}$")
                                    String telefono,
                                    @NotBlank
                                    @Pattern(regexp = "^[0-9]{1,15}$")
                                    String telefonoEmergencia,
                                    @NotBlank
                                    @Email
                                    String email,
                                    @NotNull
                                    Rol rol,
                                    @NotNull
                                    LocalDate fechaIngreso,
                                    @NotBlank
                                    String usuario,
                                    @NotBlank
                                    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{6,}$")
                                    String clave) {
}
