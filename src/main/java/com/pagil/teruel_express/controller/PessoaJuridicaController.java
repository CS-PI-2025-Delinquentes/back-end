package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.jwt.UserContextService;
import com.pagil.teruel_express.model.dto.PessoaJuridicaCreateDTO;
import com.pagil.teruel_express.model.dto.PessoaJuridicaUpdateDTO;
import com.pagil.teruel_express.model.dto.PessoaUpdateDTO;
import com.pagil.teruel_express.model.dto.SenhaUpdateDTO;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.entity.PessoaFisica;
import com.pagil.teruel_express.model.entity.PessoaJuridica;
import com.pagil.teruel_express.service.PessoaJuridicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa-juridica")
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @Autowired
    private UserContextService userContextService;

    @GetMapping
    public ResponseEntity<Page<PessoaJuridica>> findAll(Pageable pageable) {
        return ResponseEntity.ok(pessoaJuridicaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaJuridica> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaJuridicaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PessoaJuridica> save(@RequestBody @Valid   PessoaJuridicaCreateDTO pessoaJuridicaCreateDTO) {
        return ResponseEntity.ok(pessoaJuridicaService.insert(pessoaJuridicaCreateDTO));
    }

    @PutMapping
    public ResponseEntity<PessoaFisica> update(@RequestBody PessoaUpdateDTO pessoaUpdateDTO) {
        Long personId = userContextService.getCurrentUserId();
        pessoaJuridicaService.update(personId, pessoaUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<?> updateSenha(@RequestBody SenhaUpdateDTO dto) {
        Long personId = userContextService.getCurrentUserId();
        pessoaJuridicaService.updateSenha(personId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaJuridicaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}