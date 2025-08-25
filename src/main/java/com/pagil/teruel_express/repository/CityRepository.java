package com.pagil.teruel_express.repository;

import com.pagil.teruel_express.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}