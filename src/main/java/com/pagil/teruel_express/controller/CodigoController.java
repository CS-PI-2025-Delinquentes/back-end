package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.model.entity.Codigo;
import com.pagil.teruel_express.service.CodigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/codigo")
public class CodigoController {

    @Autowired
    private CodigoService codigoService;

    @PostMapping("/gerar")
    public ResponseEntity<Void> gerarCodigo(@RequestParam String email) {
        Codigo codigo = codigoService.insert(email);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/validar")
    public ResponseEntity<Boolean> validarCodigo(@RequestParam String email, String codigo) {
        boolean valido = codigoService.validar(email, codigo);

        if (valido) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @PatchMapping("/atualizar")
    public ResponseEntity<Void> atualizarSenha(@RequestParam String email, String codigo, String novaSenha) {
        codigoService.atualizarSenha(email, codigo, novaSenha);
        return ResponseEntity.noContent().build();
    }
}