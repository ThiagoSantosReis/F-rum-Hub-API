package com.tsa.forumhub.controller;

import com.tsa.forumhub.dto.DadosRequestTopico;
import com.tsa.forumhub.dto.DadosResponseTopico;
import com.tsa.forumhub.model.Topico;
import com.tsa.forumhub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public ResponseEntity<?> criarTopico(@Valid @RequestBody DadosRequestTopico dados){
        boolean topicoExistente = repository.findByTituloAndMensagem(dados.titulo(), dados.mensagem()).isPresent();
        if(topicoExistente){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tópico já existente.");
        }

        Topico topicoSalvo = repository.save(new Topico(dados));

        DadosResponseTopico response = new DadosResponseTopico(topicoSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
