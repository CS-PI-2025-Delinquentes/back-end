package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.model.dto.PessoaSenhaDTO;
import com.pagil.teruel_express.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PutMapping("/atualizar-senha/{email}")
    public ResponseEntity<String> atualizarSenha(@PathVariable String email, @RequestBody PessoaSenhaDTO dto) {
        return ResponseEntity.ok(pessoaService.atualizarSenha(email, dto));
    }
}

