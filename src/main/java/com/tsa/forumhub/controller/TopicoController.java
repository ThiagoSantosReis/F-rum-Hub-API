package com.tsa.forumhub.controller;

import com.tsa.forumhub.dto.DadosRequestTopico;
import com.tsa.forumhub.dto.DadosResponseTopico;
import com.tsa.forumhub.model.Topico;
import com.tsa.forumhub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<Page<DadosResponseTopico>> listar(Pageable pageable){
        Page<Topico> page = repository.findAll(pageable);

        Page<DadosResponseTopico> responsePage = page.map(DadosResponseTopico::new);

        return ResponseEntity.ok(responsePage);
    }




}
