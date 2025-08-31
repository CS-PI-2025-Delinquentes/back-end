package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.dto.AvaliacaoGetDTO;
import com.pagil.teruel_express.model.dto.AvaliacaoUpdateDTO;
import com.pagil.teruel_express.model.entity.Avaliacao;
import com.pagil.teruel_express.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Avaliacao insert (Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao findById(Long id) {
        return avaliacaoRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Avaliacao com id %d não encontrado", id))
        );
    }

    public AvaliacaoGetDTO findByIdGet(Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Avaliacao com id %d não encontrado", id))
        );
        return new AvaliacaoGetDTO(avaliacao);
    }

    public Page<AvaliacaoGetDTO> findAll(Pageable pageable) {
        Page<Avaliacao> avaliacoes = avaliacaoRepository.findAll(pageable);
        return avaliacoes.map(avaliacao -> new AvaliacaoGetDTO(avaliacao));
    }

    public Avaliacao update(Long id, AvaliacaoUpdateDTO avaliacaoUpdateDTO) {
        Avaliacao avaliacaoBank = findById(id);

        avaliacaoBank.setNota(avaliacaoUpdateDTO.getNota());
        avaliacaoBank.setDescricao(avaliacaoUpdateDTO.getDescricao());

        return avaliacaoRepository.save(avaliacaoBank);
    }

    public void delete(Long id) {
        Avaliacao avaliacaoBank = findById(id);
        avaliacaoRepository.delete(avaliacaoBank);
    }
}