package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.model.dto.EstadoDTO;
import com.pagil.teruel_express.model.entity.Estado;
import com.pagil.teruel_express.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/state")
public class EstadoController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public ResponseEntity<Page<Estado>> findAll(Pageable pageable) {
        return ResponseEntity.ok(stateService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> findById(@PathVariable Long id) {
        return ResponseEntity.ok(stateService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Estado> save(@RequestBody Estado estado) {
        return ResponseEntity.ok(stateService.insert(estado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> update(@PathVariable Long id, @RequestBody EstadoDTO estadoDTO) {
        return ResponseEntity.ok(stateService.update(id, estadoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        stateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}