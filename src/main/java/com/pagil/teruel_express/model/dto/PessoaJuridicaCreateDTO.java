package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.Pessoa;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PessoaJuridicaCreateDTO {

    @NotNull
    private String nomeFantasia;

    @NotNull
    @Size(min = 14, max = 14)
    private String cnpj;

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
