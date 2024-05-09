package com.fimeco.fimeco.domain.user;

public record AuthResponse(String usernameOrEmail,
                           String message,
                           String jwt,
                           boolean status) {
}
