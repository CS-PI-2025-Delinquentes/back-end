package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.dto.EstadoDTO;
import com.pagil.teruel_express.model.entity.Estado;
import com.pagil.teruel_express.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado insert(EstadoDTO estadoDTO) {
        Estado estado = new Estado();
        estado.setNome(estadoDTO.getNome());
        estado.setUf(estadoDTO.getUf());

        return estadoRepository.save(estado);
    }


    public Estado update(Long id, EstadoDTO estadoDTO) {
        Estado estadoBank = findById(id);

        estadoBank.setNome(estadoDTO.getNome());
        estadoBank.setUf(estadoDTO.getUf());

        return estadoRepository.save(estadoBank);
    }

    public void delete(Long id) {
        Estado estadoBank = findById(id);
        estadoRepository.delete(estadoBank);
    }

    public Estado findById(Long id) {
        return estadoRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Estado com id %s não encontrado", id))
        );
    }

    public Page<Estado> findAll(Pageable pageable) {
        return estadoRepository.findAll(pageable);
    }
}