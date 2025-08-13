package com.tsa.forumhub.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosRequestLogin(
        @NotBlank
        String login,
        @NotBlank
        String senha
) {
}
