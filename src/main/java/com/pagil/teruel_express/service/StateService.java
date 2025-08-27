package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.entity.State;
import com.pagil.teruel_express.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public State insert(State state) {
        return stateRepository.save(state);
    }

    public State update(State state) {
        State stateBank = findById(state.getId());
        stateBank.setName(state.getName());
        stateBank.setAcronym(state.getAcronym());

        return stateRepository.save(stateBank);
    }

    public void delete(Long id) {
        State stateBank = findById(id);
        stateRepository.delete(stateBank);
    }

    public State findById(Long id) {
        return stateRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Estado ocm id id %s não encontrado", id))
        );
    }

    public Page<State> findAll(Pageable pageable) {
        return stateRepository.findAll(pageable);
    }
}