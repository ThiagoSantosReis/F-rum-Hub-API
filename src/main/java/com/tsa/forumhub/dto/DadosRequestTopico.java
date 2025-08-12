package com.tsa.forumhub.dto;

import com.tsa.forumhub.model.StatusTopico;
import jakarta.validation.constraints.NotBlank;

public record DadosRequestTopico(
        @NotBlank(message = "O titulo é obrigatório")
        String titulo,

        @NotBlank(message = "A mensagem é obrigatória")
        String mensagem,

        @NotBlank(message = "É necessário especificar o autor")
        String autor,

        @NotBlank(message = "É necessário especificar o curso")
        String curso
        ) {
}
