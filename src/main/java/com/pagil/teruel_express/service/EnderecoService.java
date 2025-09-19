package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.dto.EnderecoDTO;
import com.pagil.teruel_express.model.dto.mapper.EnderecoMapper;
import com.pagil.teruel_express.model.entity.Cidade;
import com.pagil.teruel_express.model.entity.Endereco;
import com.pagil.teruel_express.repository.CidadeRepository;
import com.pagil.teruel_express.repository.EnderecoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private CidadeService cidadeService;

    public Endereco insert(EnderecoDTO dto) {
        Cidade cidade = cidadeService.buscarPorNomeSeAtendida(dto.getCidade());
        Endereco endereco = EnderecoMapper.toEndereco(dto, cidade);
        return repository.save(endereco);
    }

    public Endereco findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Endereço de id: %d não encontrado", id))
        );
    }

    public EnderecoDTO findByIdDto(Long id) {
        Endereco endereco = repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Endereço de id: %d não encontrado", id))
        );
        return EnderecoMapper.toDto(endereco);
    }

}
