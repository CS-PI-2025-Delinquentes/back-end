package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.jwt.UserContextService;
import com.pagil.teruel_express.model.dto.PessoaFisicaCreateDTO;
import com.pagil.teruel_express.model.dto.PessoaUpdateDTO;
import com.pagil.teruel_express.model.dto.SenhaUpdateDTO;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.entity.PessoaFisica;
import com.pagil.teruel_express.service.PessoaFisicaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa-fisica")
@Slf4j
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @Autowired
    private UserContextService userContextService;

    @GetMapping
    public ResponseEntity<Page<PessoaFisica>> findAll(Pageable pageable) {
        return ResponseEntity.ok(pessoaFisicaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaFisica> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaFisicaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PessoaFisica> save(@RequestBody @Valid PessoaFisicaCreateDTO pessoaFisicaCreateDTO) {
        return ResponseEntity.ok(pessoaFisicaService.insert(pessoaFisicaCreateDTO));
    }

    @PutMapping
    public ResponseEntity<PessoaFisica> update(@RequestBody PessoaUpdateDTO pessoaUpdateDTO) {
        Long personId = userContextService.getCurrentUserId();
        return ResponseEntity.ok(pessoaFisicaService.update(personId, pessoaUpdateDTO));
    }

    @PatchMapping
    public ResponseEntity<?> updateSenha(@RequestBody @Valid SenhaUpdateDTO dto) {
        Long personId = userContextService.getCurrentUserId();
        pessoaFisicaService.updateSenha(personId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaFisicaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}