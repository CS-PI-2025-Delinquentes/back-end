package com.pagil.teruel_express.repository;

import com.pagil.teruel_express.model.entity.Cidade;
import com.pagil.teruel_express.model.entity.Estado;
import com.pagil.teruel_express.model.entity.StatusRota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static com.pagil.teruel_express.model.entity.StatusRota.EXCLUIDO;

public interface CidadeRepository extends JpaRepository<Cidade,Long> {

    public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);
    Optional<Cidade> findByNomeIgnoreCase(String nome);
    Optional<Cidade> findByNomeIgnoreCaseAndEstadoNomeIgnoreCase(String cidade, String estado);
    Page<Cidade> findAllByStatusNot(StatusRota status, Pageable pageable);

}