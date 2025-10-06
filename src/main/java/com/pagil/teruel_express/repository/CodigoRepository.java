package com.pagil.teruel_express.repository;

import com.pagil.teruel_express.model.entity.Codigo;
import com.pagil.teruel_express.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodigoRepository extends JpaRepository<Codigo, Long> {
    Optional<Codigo> findByPessoa(Pessoa pessoa);

}