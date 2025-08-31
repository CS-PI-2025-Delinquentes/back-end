package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.entity.PessoaFisica;
import com.pagil.teruel_express.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository repository;

    public PessoaFisica buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf).orElseThrow(
                () -> new NotFoundException("CPF não encontrado")
        );
    }

    public Pessoa.Role buscarRolePorUsername(String cpf) {
        return repository.findRoleByCpf(cpf);
    }
}
