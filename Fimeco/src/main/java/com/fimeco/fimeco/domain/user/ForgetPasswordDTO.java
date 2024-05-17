package com.fimeco.fimeco.domain.user;

import jakarta.validation.constraints.Email;

public record ForgetPasswordDTO(@Email String email) {
}
