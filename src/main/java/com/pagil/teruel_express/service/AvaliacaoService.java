package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.jwt.UserContextService;
import com.pagil.teruel_express.model.dto.AvaliacaoCreateDTO;
import com.pagil.teruel_express.model.dto.AvaliacaoResponseDTO;
import com.pagil.teruel_express.model.dto.AvaliacaoUpdateDTO;
import com.pagil.teruel_express.model.entity.Avaliacao;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.repository.AvaliacaoRepository;
import com.pagil.teruel_express.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private UserContextService userContextService;

    public AvaliacaoResponseDTO insert (AvaliacaoCreateDTO avaliacaoCreateDTO) {

        Long pessoaLogada = userContextService.getCurrentUserId();

        Pessoa pessoa = pessoaRepository.findById(pessoaLogada)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(avaliacaoCreateDTO.getNota());
        avaliacao.setDescricao(avaliacaoCreateDTO.getDescricao());
        avaliacao.setPessoa(pessoa);

        avaliacaoRepository.save(avaliacao);

        return new AvaliacaoResponseDTO(avaliacao);
    }

    public Avaliacao findById(Long id) {
        return avaliacaoRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Avaliacao com id %d não encontrado", id))
        );
    }

    public AvaliacaoResponseDTO findByIdGet(Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Avaliacao com id %d não encontrado", id))
        );
        return new AvaliacaoResponseDTO(avaliacao);
    }

    public Page<AvaliacaoResponseDTO> findAll(Pageable pageable) {
        Page<Avaliacao> avaliacoes = avaliacaoRepository.findAll(pageable);
        return avaliacoes.map(avaliacao -> new AvaliacaoResponseDTO(avaliacao));
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