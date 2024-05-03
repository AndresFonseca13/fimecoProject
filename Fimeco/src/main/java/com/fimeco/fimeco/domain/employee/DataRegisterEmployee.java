package com.fimeco.fimeco.domain.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record DataRegisterEmployee(@NotBlank
                                    String document,
                                   @NotBlank
                                    String name,
                                   @NotBlank
                                    String lastName,
                                   @NotNull
                                    LocalDate birthdate,
                                   @NotBlank
                                    @Pattern(regexp = "^[0-9]{1,15}$")
                                    String phone,
                                   @NotBlank
                                    @Pattern(regexp = "^[0-9]{1,15}$")
                                    String emergencyPhone,
                                   @NotBlank
                                    @Email
                                    String email,
                                   @NotNull
                                    Position position,
                                   @NotNull
                                    LocalDate dateEntry,
                                   @NotNull
                                    String username){
}
