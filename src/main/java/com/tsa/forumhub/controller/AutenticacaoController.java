package com.tsa.forumhub.controller;

import com.tsa.forumhub.dto.DadosRequestLogin;
import com.tsa.forumhub.dto.DadosResponseToken;
import com.tsa.forumhub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody DadosRequestLogin dados) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
            var auth = authManager.authenticate(authToken);
            String jwt = tokenService.gerarToken(auth.getName());
            return ResponseEntity.ok(new DadosResponseToken(jwt));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
