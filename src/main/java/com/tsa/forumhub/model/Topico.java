package com.tsa.forumhub.model;

import com.tsa.forumhub.dto.DadosRequestTopico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topicos",
uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo", "mensagem"})})
@Getter
@Setter
@NoArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensagem;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NAO_RESPONDIDO;

    @NotBlank
    private String autor;

    private String curso;

    public Topico(DadosRequestTopico dados){
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.autor = dados.autor();
        this.curso = dados.curso();
    }

}
