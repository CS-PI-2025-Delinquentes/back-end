package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.model.dto.AvaliacaoCreateDTO;
import com.pagil.teruel_express.model.dto.AvaliacaoGetDTO;
import com.pagil.teruel_express.model.dto.AvaliacaoUpdateDTO;
import com.pagil.teruel_express.model.entity.Avaliacao;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.repository.PessoaRepository;
import com.pagil.teruel_express.service.AvaliacaoService;
import jakarta.persistence.EntityNotFoundException;
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

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public ResponseEntity<Page<AvaliacaoGetDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(avaliacaoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoGetDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoService.findByIdGet(id));
    }

    @PostMapping
    public ResponseEntity<?> criarAvaliacao(@RequestBody AvaliacaoCreateDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(dto.getNota());
        avaliacao.setDescricao(dto.getDescricao());
        avaliacao.setPessoa(pessoa);

        return ResponseEntity.ok(avaliacaoService.insert(avaliacao));
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