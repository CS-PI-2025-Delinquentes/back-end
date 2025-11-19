package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.model.dto.EstadoDTO;
import com.pagil.teruel_express.model.entity.Estado;
import com.pagil.teruel_express.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_DEV')")
    public ResponseEntity<Page<Estado>> findAll(Pageable pageable) {
        return ResponseEntity.ok(estadoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DEV')")
    public ResponseEntity<Estado> findById(@PathVariable Long id) {
        return ResponseEntity.ok(estadoService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_DEV')")
    public ResponseEntity<Estado> save(@RequestBody EstadoDTO estadoDTO) {
        return ResponseEntity.ok(estadoService.insert(estadoDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DEV')")
    public ResponseEntity<Estado> update(@PathVariable Long id, @RequestBody EstadoDTO estadoDTO) {
        return ResponseEntity.ok(estadoService.update(id, estadoDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DEV')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}