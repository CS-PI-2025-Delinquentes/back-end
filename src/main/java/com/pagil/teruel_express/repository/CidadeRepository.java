package com.pagil.teruel_express.repository;

import com.pagil.teruel_express.model.entity.Cidade;
import com.pagil.teruel_express.model.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade,Long> {

    public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);
}