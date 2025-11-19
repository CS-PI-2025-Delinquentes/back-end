package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.BusinessLogicException;
import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.jwt.UserContextService;
import com.pagil.teruel_express.model.dto.OrcamentoDTO;
import com.pagil.teruel_express.model.dto.mapper.PedidoMapper;
import com.pagil.teruel_express.model.entity.Endereco;
import com.pagil.teruel_express.model.entity.Pacote;
import com.pagil.teruel_express.model.entity.Pedido;
import com.pagil.teruel_express.model.entity.StatusPedido;
import com.pagil.teruel_express.repository.PacoteRepository;
import com.pagil.teruel_express.repository.PedidoRepository;
import com.pagil.teruel_express.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private UserContextService userService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PacoteRepository pacoteRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Transactional
    public void insert(OrcamentoDTO dto) {
        Endereco origem = enderecoService.insert(dto.getOrigem());
        Endereco destino = enderecoService.insert(dto.getDestino());
        Pedido pedido = PedidoMapper.toPedido(dto, origem, destino);
        pedido.setPessoa(userService.getCurrentPessoa());
        pedido.setObservacao(dto.getObservacao());
        Pedido pedidoBank = repository.save(pedido);
        pacoteRepository.saveAll(PedidoMapper.toListPacote(dto.getPacotes(), pedidoBank));
    }

    public List<Pedido> findAllByPessoa(){
        return repository.findAllByPessoa(userService.getCurrentPessoa());
    }

    public List<Pedido> findAll(){
        return repository.findAll();
    }

    public Page<Pedido> findAllPaged(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Pedido> findAllByPessoaPaged(Pageable pageable) {
        return repository.findAllByPessoa(userService.getCurrentPessoa(), pageable);
    }

    public Page<Pedido> findAllByPessoaIdPaged(Long id, Pageable pageable) {
        try {
            return repository.findAllByPessoa(pessoaRepository.findById(id).get(), pageable);
        } catch (NoSuchElementException e) {
            throw new NotFoundException(String.format("Pessoa com id: %d não encontrada", id));
        }
    }

    public Pedido findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Pedido de id: %d não encontrado", id))
        );
    }

    public Page<Pedido> findAllByStatusPedido(StatusPedido statusPedido, Pageable pageable) {
        ArrayList<StatusPedido> col = new ArrayList<>() ;
        return repository.findByStatusIn(col,pageable);
    }

    public void delete(Long id) {
        Pedido pedido = findById(id);
        if(pedido.getStatus() != StatusPedido.PENDENTE) throw new BusinessLogicException("Não é permitido cancelar um pedido não pendente");
        repository.delete(pedido);
    }

    public Pedido avaliarPedido(Long id, boolean aceito) {
        Pedido pedido = findById(id);
        if(pedido.getStatus() != StatusPedido.PENDENTE) throw new BusinessLogicException("Não é permitido mudar um status não pendente");
        if(aceito) pedido.setStatus(StatusPedido.ACEITO);
        else pedido.setStatus(StatusPedido.RECUSADO);
        return repository.save(pedido);
    }

    public List<Pacote> findPacotesByPedidoId(Long id) {
        return pacoteRepository.findAllByPedidoId(id);
    }

}