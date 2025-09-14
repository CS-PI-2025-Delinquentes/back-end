package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.CityStateUniqueViolationException;
import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.exception.RouteNotAvailableException;
import com.pagil.teruel_express.model.dto.CidadeDTO;
import com.pagil.teruel_express.model.entity.Cidade;
import com.pagil.teruel_express.model.entity.Estado;
import com.pagil.teruel_express.model.entity.StatusRota;
import com.pagil.teruel_express.repository.CidadeRepository;
import com.pagil.teruel_express.repository.EstadoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade insert(CidadeDTO cidadeDTO) {
        Estado estadoBank = estadoRepository.findById(cidadeDTO.getEstadoId()).orElseThrow(
                () -> new NotFoundException("Estado não encontrado")
        );

        Optional<Cidade> cidadeBank = cidadeRepository.findByNomeAndEstado(cidadeDTO.getNome(), estadoBank);
        if(cidadeBank.isPresent()) {
            throw new CityStateUniqueViolationException("Cidade já cadastrada nesse Estado");
        }

        Cidade cidade = new Cidade();
        cidade.setNome(cidadeDTO.getNome());
        cidade.setStatus(cidadeDTO.getStatus());
        cidade.setEstado(estadoBank);

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

    private Cidade findByName (String nome){
        return cidadeRepository.findByNomeIgnoreCase(nome).orElseThrow(
                () -> {
                    log.warn("Verifique se a cidade está escrita corretamente ignorando Case");
                    return new NotFoundException(String.format("Cidade com nome: %s não encontrada", nome));
                }
        );
    }

    public Cidade buscarPorNomeSeAtendida (String nome){
        Cidade rota = findByName(nome);
        if(rota.getStatus() != StatusRota.ATIVO) {
            throw new RouteNotAvailableException(String.format("A rota: %s não está sendo atendida", nome));
        }
        else return rota;
    }

    public Page<Cidade> findAll(Pageable pageable) {
        return cidadeRepository.findAll(pageable);
    }
}