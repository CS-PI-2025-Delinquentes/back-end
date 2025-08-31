package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.model.dto.PessoaJuridicaCreateDTO;
import com.pagil.teruel_express.model.dto.PessoaJuridicaUpdateDTO;
import com.pagil.teruel_express.model.entity.PessoaJuridica;
import com.pagil.teruel_express.service.PessoaJuridicaService;
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

    @GetMapping
    public ResponseEntity<Page<PessoaJuridica>> findAll(Pageable pageable) {
        return ResponseEntity.ok(pessoaJuridicaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaJuridica> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaJuridicaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PessoaJuridica> save(@RequestBody PessoaJuridicaCreateDTO pessoaJuridicaCreateDTO) {
        return ResponseEntity.ok(pessoaJuridicaService.insert(pessoaJuridicaCreateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaJuridica> update(@PathVariable Long id, @RequestBody PessoaJuridicaUpdateDTO pessoaJuridicaUpdateDTO) {
        return ResponseEntity.ok(pessoaJuridicaService.update(id,pessoaJuridicaUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaJuridicaService.delete(id);
        return ResponseEntity.notFound().build();
    }
}