package com.pagil.teruel_express.repository;

import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.entity.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

    Optional<PessoaJuridica> findByCnpj(String cpf);
    @Query("SELECT p.role FROM PessoaJuridica p WHERE p.cnpj = ?1")
    Pessoa.Role findRoleByCnpj(String cnpj);
}
