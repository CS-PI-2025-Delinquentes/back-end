package com.pagil.teruel_express.repository;

import com.pagil.teruel_express.model.entity.Avaliacao;
import com.pagil.teruel_express.model.entity.Codigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodigoRepository extends JpaRepository<Codigo, Long> {
}
