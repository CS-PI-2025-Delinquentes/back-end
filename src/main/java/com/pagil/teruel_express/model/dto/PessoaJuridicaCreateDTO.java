package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PessoaJuridicaCreateDTO {

    @NotBlank
    private String nomeFantasia;

    @NotBlank
    @Size(min = 14, max = 14)
    private String cnpj;

    @NotBlank
    private String email;

    @NotBlank
    private String telefone;

    @NotBlank
    @Size(min = 8)
    private String senha;

    @NotBlank
    private Pessoa.Role role;
}
