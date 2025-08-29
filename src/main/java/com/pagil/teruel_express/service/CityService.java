package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.CityStateUniqueViolationException;
import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.dto.CityDTO;
import com.pagil.teruel_express.model.entity.City;
import com.pagil.teruel_express.model.entity.State;
import com.pagil.teruel_express.repository.CityRepository;
import com.pagil.teruel_express.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private StateRepository stateRepository;

    public City insert(City city) {
        Optional<City> cityBank = cityRepository.findByNameAndState(city.getName(), city.getState());
        if(cityBank.isPresent()) {
            throw new CityStateUniqueViolationException("Cidade já cadastrada nesse Estado");
        }

        return cityRepository.save(city);
    }

    public City update(Long id, CityDTO cityDTO) {
        City cityBank = findById(id);

        cityBank.setName(cityDTO.getName());
        cityBank.setStatus(cityDTO.getStatus());

        State stateBank = stateRepository.findById(cityDTO.getEstadoId()).orElseThrow(
                () -> new NotFoundException("Estado não encontrado")
        );

        cityBank.setState(stateBank);

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