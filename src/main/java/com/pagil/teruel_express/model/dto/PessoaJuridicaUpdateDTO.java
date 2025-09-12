package com.pagil.teruel_express.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PessoaJuridicaUpdateDTO {

    @NotBlank
    private String nomeFantasia;

    @NotBlank
    private String email;

    @NotBlank
    private String telefone;
}