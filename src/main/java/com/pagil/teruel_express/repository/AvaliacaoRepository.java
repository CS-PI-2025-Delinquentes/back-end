package com.pagil.teruel_express.repository;

import com.pagil.teruel_express.model.entity.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    public Page<Avaliacao> findAllByPessoaId(Long id, Pageable pageable);
}