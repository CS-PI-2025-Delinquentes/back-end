package com.pagil.teruel_express.service;

import com.pagil.teruel_express.model.dto.CidadeDTO;
import com.pagil.teruel_express.model.entity.Cidade;
import com.pagil.teruel_express.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrcamentoService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Page<Cidade> teste(Pageable pageable) {
        return cidadeRepository.findAll(pageable);
    }
}
