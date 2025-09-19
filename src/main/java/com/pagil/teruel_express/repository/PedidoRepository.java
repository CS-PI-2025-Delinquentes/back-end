package com.pagil.teruel_express.repository;

import com.pagil.teruel_express.model.entity.Pedido;
import com.pagil.teruel_express.model.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findAllByPessoa(Pessoa pessoa);
    Page<Pedido> findAllByPessoa(Pessoa pessoa, Pageable pageable);

}
