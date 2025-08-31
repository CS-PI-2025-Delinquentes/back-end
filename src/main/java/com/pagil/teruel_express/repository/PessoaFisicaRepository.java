package com.pagil.teruel_express.repository;

import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.entity.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

    Optional<PessoaFisica> findByCpf(String cpf);
    @Query("SELECT p.role FROM PessoaFisica p WHERE p.cpf = ?1")
    Pessoa.Role findRoleByCpf(String cpf);
}
