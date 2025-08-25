package com.pagil.teruel_express.service;

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
        State stateRegistered = stateRepository.save(state);
        return stateRegistered;
    }

    public State update(State state) {
        State stateBank = stateRepository.findById(state.getId()).orElse(null);

        stateBank.setName(state.getName());
        stateBank.setAcronym(state.getAcronym());

        return stateRepository.save(stateBank);
    }

    public void delete(Long id) {
        State stateBank = findById(id);
        stateRepository.delete(stateBank);
    }

    public State findById(Long id) {
        State stateBank = stateRepository.findById(id).orElse(null);
        return stateRepository.save(stateBank);
    }

    public Page<State> findAll(Pageable pageable) {
        return stateRepository.findAll(pageable);
    }
}