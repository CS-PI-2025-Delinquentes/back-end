package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.Pessoa;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PessoaFisicaUpdateDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String telefone;
}
