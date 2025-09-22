package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.dto.PessoaSenhaDTO;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public void atualizarSenha(String email, PessoaSenhaDTO pessoaSenhaDTO){
        Pessoa pessoaBank = pessoaRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Email não econtrado")
        );
        pessoaBank.setSenha(pessoaSenhaDTO.getSenha());
        pessoaRepository.save(pessoaBank);
    }
}
