package com.tsa.forumhub.repository;

import com.tsa.forumhub.model.Topico;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTituloAndMensagem(String titulo, String mensagem);
}
