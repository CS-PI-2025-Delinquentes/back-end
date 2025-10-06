package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.BusinessLogicException;
import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.jwt.UserContextService;
import com.pagil.teruel_express.model.entity.Pacote;
import com.pagil.teruel_express.model.entity.Pedido;
import com.pagil.teruel_express.model.entity.StatusPedido;
import com.pagil.teruel_express.repository.PacoteRepository;
import com.pagil.teruel_express.repository.PedidoRepository;
import com.pagil.teruel_express.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    public Pedido insert(Pedido pedido) {
        return repository.save(pedido);
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
