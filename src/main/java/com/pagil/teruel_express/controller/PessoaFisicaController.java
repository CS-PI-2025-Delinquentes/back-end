package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.model.dto.PessoaFisicaCreateDTO;
import com.pagil.teruel_express.model.dto.PessoaFisicaUpdateDTO;
import com.pagil.teruel_express.model.entity.PessoaFisica;
import com.pagil.teruel_express.service.PessoaFisicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa-fisica")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

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

    @PutMapping("/{id}")
    public ResponseEntity<PessoaFisica> update(@PathVariable Long id, @RequestBody PessoaFisicaUpdateDTO pessoaFisicaUpdateDTO) {
        return ResponseEntity.ok(pessoaFisicaService.update(id, pessoaFisicaUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaFisicaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}