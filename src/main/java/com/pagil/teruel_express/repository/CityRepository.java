package com.pagil.teruel_express.repository;

import com.pagil.teruel_express.model.entity.City;
import com.pagil.teruel_express.model.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {

    public Optional<City> findByNameAndState(String name, State state);
}