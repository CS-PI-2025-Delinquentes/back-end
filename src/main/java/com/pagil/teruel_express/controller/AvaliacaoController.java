package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.model.dto.AvaliacaoCreateDTO;
import com.pagil.teruel_express.model.dto.AvaliacaoResponseDTO;
import com.pagil.teruel_express.model.dto.AvaliacaoUpdateDTO;
import com.pagil.teruel_express.model.dto.PageableDTO;
import com.pagil.teruel_express.model.dto.mapper.PageableMapper;
import com.pagil.teruel_express.model.entity.Avaliacao;
import com.pagil.teruel_express.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<PageableDTO> findAll(Pageable pageable) {
        return ResponseEntity.ok(
                PageableMapper.toDto(avaliacaoService.findAll(pageable))
        );
    }

    @GetMapping("/clientes")
    public ResponseEntity<PageableDTO> findAllByClienteId(Pageable pageable) {
        return ResponseEntity.ok(
                PageableMapper.toDto(avaliacaoService.findAllById(pageable))
        );
    }

    @GetMapping("/landing")
    public ResponseEntity<PageableDTO> findAllLanding(@RequestParam(defaultValue = "3") Integer nota, Pageable pageable) {
        return ResponseEntity.ok(
                PageableMapper.toDto(avaliacaoService.findLastLanding(nota, pageable))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoService.findByIdGet(id));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> criarAvaliacao(@RequestBody AvaliacaoCreateDTO avaliacaoCreateDTO) {
        return ResponseEntity.ok(avaliacaoService.insert(avaliacaoCreateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> update(@PathVariable Long id, @RequestBody AvaliacaoUpdateDTO avaliacaoUpdateDTO) {
        return ResponseEntity.ok(avaliacaoService.update(id, avaliacaoUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        avaliacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}