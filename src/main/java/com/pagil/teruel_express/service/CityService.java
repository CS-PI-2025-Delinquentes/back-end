package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.entity.City;
import com.pagil.teruel_express.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City insert(City city) {
        return cityRepository.save(city);
    }

    // passar o id da cidade
    public City update(City city) {
        City cityBank = findById(city.getId());

        cityBank.setName(city.getName());
        cityBank.setStatus(city.getStatus());
        // Buscar por estado antes de atualizar o estado
        cityBank.setState(city.getState());

        return cityRepository.save(cityBank);
    }

    public void delete(Long id) {
        City cityBank = findById(id);
        cityRepository.delete(cityBank);
    }

    public City findById(Long id) {
        City cityBank = cityRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Cidade com id %d não encontrado", id))
        );
        return cityRepository.save(cityBank);
    }

    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }
}