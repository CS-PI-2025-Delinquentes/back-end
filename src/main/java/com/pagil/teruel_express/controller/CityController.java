package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.model.dto.CityDTO;
import com.pagil.teruel_express.model.entity.City;
import com.pagil.teruel_express.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<Page<City>> findAll(Pageable pageable) {
        return ResponseEntity.ok(cityService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.findById(id));
    }

    @PostMapping
    public ResponseEntity<City> save(@RequestBody City city) {
        return ResponseEntity.ok(cityService.insert(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> update(@PathVariable Long id, @RequestBody CityDTO cityDTO) {
        return ResponseEntity.ok(cityService.update(id, cityDTO));
    }

    @DeleteMapping("/{id}")
        return ResponseEntity.noContent().build();
    }
}