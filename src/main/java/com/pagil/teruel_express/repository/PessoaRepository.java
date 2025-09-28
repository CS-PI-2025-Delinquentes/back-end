package com.pagil.teruel_express.repository;

import java.util.Optional;
import com.pagil.teruel_express.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByEmail(String email);
}