package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.CityStateUniqueViolationException;
import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.dto.CidadeDTO;
import com.pagil.teruel_express.model.entity.Cidade;
import com.pagil.teruel_express.model.entity.Estado;
import com.pagil.teruel_express.repository.CidadeRepository;
import com.pagil.teruel_express.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade insert(Cidade cidade) {
        Optional<Cidade> cityBank = cidadeRepository.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
        if(cityBank.isPresent()) {
            throw new CityStateUniqueViolationException("Cidade já cadastrada nesse Estado");
        }

        return cidadeRepository.save(cidade);
    }

    public Cidade update(Long id, CidadeDTO cidadeDTO) {
        Cidade cidadeBank = findById(id);

        cidadeBank.setNome(cidadeDTO.getNome());
        cidadeBank.setStatus(cidadeDTO.getStatus());

        Estado estadoBank = estadoRepository.findById(cidadeDTO.getEstadoId()).orElseThrow(
                () -> new NotFoundException("Estado não encontrado")
        );

        cidadeBank.setEstado(estadoBank);

        return cidadeRepository.save(cidadeBank);
    }

    public void delete(Long id) {
        Cidade cidadeBank = findById(id);
        cidadeRepository.delete(cidadeBank);
    }

    public Cidade findById(Long id) {
        Cidade cidadeBank = cidadeRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Cidade com id %d não encontrado", id))
        );
        return cidadeBank;
    }

    public Page<Cidade> findAll(Pageable pageable) {
        return cidadeRepository.findAll(pageable);
    }
}