package com.fimeco.fimeco.domain.user;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(@NotBlank String usernameOrEmail, @NotBlank String password) {
}
