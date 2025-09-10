package com.pagil.teruel_express.repository;

import com.pagil.teruel_express.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}