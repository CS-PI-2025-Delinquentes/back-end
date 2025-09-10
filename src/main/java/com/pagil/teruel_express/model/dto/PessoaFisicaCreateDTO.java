package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.Pessoa;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PessoaFisicaCreateDTO {

    @NotNull
    private String nome;

    @NotNull
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    private String email;

    @NotNull
    private String telefone;

    @NotNull
    @Size(min = 8)
    private String senha;

    @NotNull
    private Pessoa.Role role;
}