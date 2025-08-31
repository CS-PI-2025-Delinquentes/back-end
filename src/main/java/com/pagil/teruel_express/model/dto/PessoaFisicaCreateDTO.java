package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.Pessoa;
import lombok.Data;

@Data
public class PessoaFisicaCreateDTO {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String senha;
    private Pessoa.Role role;
}