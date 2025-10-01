package com.pagil.teruel_express.repository;

import com.pagil.teruel_express.model.entity.Pacote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacoteRepository extends JpaRepository<Pacote, Long> {

    List<Pacote> findByPedidoId(Long pedidoId);
}
