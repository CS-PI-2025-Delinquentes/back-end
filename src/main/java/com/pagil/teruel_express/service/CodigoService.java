package com.pagil.teruel_express.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.pagil.teruel_express.repository.CodigoRepository;
import com.pagil.teruel_express.utils.NumberGenerator;

@Service
public class CodigoService {

    @Autowired
    private CodigoRepository codigoRepository;

    @Autowired
    private NumberGenerator numberGenerator;
    
    numberGenerator.generateNumberConfirmation();
}
