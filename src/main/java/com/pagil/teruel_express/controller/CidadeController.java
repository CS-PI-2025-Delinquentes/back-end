package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.model.dto.CidadeDTO;
import com.pagil.teruel_express.model.entity.Cidade;
import com.pagil.teruel_express.service.CidadeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<Page<Cidade>> findAll(Pageable pageable) {
        return ResponseEntity.ok(cidadeService.findAllNotExcluded(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cidadeService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<Cidade> save(@RequestBody CidadeDTO cidadeDTO) {
        return ResponseEntity.ok(cidadeService.insert(cidadeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> update(@PathVariable Long id, @RequestBody CidadeDTO cidadeDTO) {
        return ResponseEntity.ok(cidadeService.update(id, cidadeDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cidadeService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping({"/{id}"})
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<Void> toggleActive(@PathVariable Long id) {
        cidadeService.toggleActive(id);
        return ResponseEntity.noContent().build();
    }
}