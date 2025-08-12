package com.tsa.forumhub.dto;

import com.tsa.forumhub.model.StatusTopico;
import com.tsa.forumhub.model.Topico;

import java.time.LocalDateTime;

public record DadosResponseTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String status,
        String autor,
        String curso


) {

    public DadosResponseTopico(Topico dados) {
        this(
                dados.getId(),
                dados.getTitulo(),
                dados.getMensagem(),
                dados.getDataCriacao(),
                dados.getStatus().name(),
                dados.getAutor(),
                dados.getCurso()
        );
    }


}
