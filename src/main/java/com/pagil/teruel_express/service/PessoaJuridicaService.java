package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.entity.PessoaJuridica;
import com.pagil.teruel_express.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository repository;

    public PessoaJuridica buscarPorCnpj(String cnpj) {
        return repository.findByCnpj(cnpj).orElseThrow(
                () -> new NotFoundException("CNPJ não encontrado")
        );
    }

    public Pessoa.Role buscarRolePorUsername(String cnpj) {
        return repository.findRoleByCnpj(cnpj);
    }

}
