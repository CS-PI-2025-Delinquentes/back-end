package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.model.entity.State;
import com.pagil.teruel_express.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public ResponseEntity<Page<State>> findAll(Pageable pageable) {
        return ResponseEntity.ok(stateService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> findById(Long id) {
        return ResponseEntity.ok(stateService.findById(id));
    }

    @PostMapping
    public ResponseEntity<State> save(@RequestBody State state) {
        return ResponseEntity.ok(stateService.insert(state));
    }

    @PutMapping
    public ResponseEntity<State> update(@RequestBody State state) {
        return ResponseEntity.ok(stateService.update(state));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        stateService.delete(id);
        return ResponseEntity.ok("Excluído");
    }
}