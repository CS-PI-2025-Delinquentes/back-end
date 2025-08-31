package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.exception.handler.ErrorMessage;
import com.pagil.teruel_express.jwt.JwtToken;
import com.pagil.teruel_express.jwt.JwtUserDetailsService;
import com.pagil.teruel_express.model.dto.LoginDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final JwtUserDetailsService detailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginDto dto, HttpServletRequest request) {
        log.info("Processo de autenticação pelo login {}", dto.getCpfCnpj());
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getCpfCnpj(), dto.getSenha());
            authenticationManager.authenticate(authenticationToken);
            JwtToken token = detailsService.getTokenAuthenticated(dto.getCpfCnpj());
            return ResponseEntity.ok(token);
        } catch (AuthenticationException ex) {
            log.warn("Bad credentials from username {}", dto.getCpfCnpj());
            return ResponseEntity.badRequest()
                    .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais Inválidas"));
        }
    }

    @GetMapping("/nome")
    public ResponseEntity<String> getNome() {
        String nome = detailsService.getNomeLogado(SecurityContextHolder.getContext().getAuthentication().getName());
        log.info("Nome ou nome fantasia {}",nome);
        return ResponseEntity.ok(nome);
    }
}
