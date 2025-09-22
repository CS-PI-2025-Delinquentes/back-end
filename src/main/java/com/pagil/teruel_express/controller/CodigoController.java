package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.model.entity.Codigo;
import com.pagil.teruel_express.service.CodigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/codigo")
public class CodigoController {

    @Autowired
    private CodigoService codigoService;

    // Gerar e enviar código para o email
    @PostMapping("/gerar")
    public ResponseEntity<Codigo> gerarCodigo(@RequestParam String email) {
        Codigo codigo = codigoService.insert(email);
        return ResponseEntity.ok(codigo);
    }

    // Validar código enviado pelo usuário
    @PostMapping("/validar")
    public ResponseEntity<String> validarCodigo(@RequestParam String email,
                                                @RequestParam String codigo) {
        boolean valido = codigoService.validar(email, codigo);

        if (valido) {
            return ResponseEntity.ok("Código válido!");
        } else {
            return ResponseEntity.badRequest().body("Código inválido!");
        }
    }
}