package com.tsa.forumhub.controller;

import com.tsa.forumhub.dto.DadosRequestLogin;
import com.tsa.forumhub.dto.DadosRequestUsuario;
import com.tsa.forumhub.dto.DadosResponseToken;
import com.tsa.forumhub.model.Usuario;
import com.tsa.forumhub.repository.UsuarioRepository;
import com.tsa.forumhub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



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

    @PostMapping("/usuarios")
    public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody DadosRequestUsuario dados) {
        if(usuarioRepository.findByLogin(dados.login()).isPresent()) {
            return ResponseEntity.status(409).body("Login já existe");
        }

        String role = "ROLE_USER";
        if(dados.role() != null) {
            if(dados.role().equalsIgnoreCase("ROLE_ADMIN")) {
                role = "ROLE_ADMIN";
            } else if(!dados.role().equalsIgnoreCase("ROLE_USER")) {
                return ResponseEntity.badRequest().body("Role inválida. Use ROLE_USER ou ROLE_ADMIN.");
            }
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(dados.login());
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        usuario.setRole(role);



        usuarioRepository.save(usuario);
        return ResponseEntity.status(201).body(usuario);
    }
}
