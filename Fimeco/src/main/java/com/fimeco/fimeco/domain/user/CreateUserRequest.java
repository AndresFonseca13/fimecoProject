package com.fimeco.fimeco.domain.user;

import com.fimeco.fimeco.domain.Role.AuthCreateRoleRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(@NotBlank String username,
                                @NotBlank String password,
                                @NotBlank String email,
                                @NotBlank String firstName,
                                @NotBlank String lastName,
                                String phone) {
}
