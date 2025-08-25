package com.pagil.teruel_express.repository;

import com.pagil.teruel_express.model.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}