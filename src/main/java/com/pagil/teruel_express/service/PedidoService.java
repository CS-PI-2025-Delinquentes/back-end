package com.pagil.teruel_express.service;

import com.pagil.teruel_express.jwt.UserContextService;
import com.pagil.teruel_express.model.entity.Pedido;
import com.pagil.teruel_express.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private UserContextService userService;

    public Pedido insert(Pedido pedido) {
        return repository.save(pedido);
    }

    public List<Pedido> findAllByPessoa(){
        return repository.findAllByPessoa(userService.getCurrentPessoa());
    }

    public List<Pedido> findAll(){
        return repository.findAll();
    }

}
