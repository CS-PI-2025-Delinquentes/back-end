package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.dto.EnderecoDTO;
import com.pagil.teruel_express.model.dto.mapper.EnderecoMapper;
import com.pagil.teruel_express.model.entity.Cidade;
import com.pagil.teruel_express.model.entity.Endereco;
import com.pagil.teruel_express.repository.CidadeRepository;
import com.pagil.teruel_express.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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

    public List<Endereco> insertAll(EnderecoDTO origem, EnderecoDTO destino) {
        Cidade cidadeOrigem = cidadeService.buscarPorNomeSeAtendida(origem.getCidade());
        Endereco enderecoOrigem = EnderecoMapper.toEndereco(origem, cidadeOrigem);
        Cidade cidadeDestino = cidadeService.buscarPorNomeSeAtendida(destino.getCidade());
        Endereco enderecoDestino = EnderecoMapper.toEndereco(destino, cidadeDestino);
        return repository.saveAll(Arrays.asList(enderecoOrigem, enderecoDestino));
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
