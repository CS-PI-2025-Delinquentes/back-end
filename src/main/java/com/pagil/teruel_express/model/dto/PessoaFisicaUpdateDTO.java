package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.Pessoa;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PessoaFisicaUpdateDTO {

    @NotNull
    private String nome;

    @NotNull
    private String email;

    @NotNull
    private String telefone;
}
