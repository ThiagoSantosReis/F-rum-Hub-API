package com.tsa.forumhub.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosRequestUsuario(@NotBlank String login, @NotBlank String senha, String role) {
}
