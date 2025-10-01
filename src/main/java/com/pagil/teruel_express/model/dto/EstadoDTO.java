package com.pagil.teruel_express.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EstadoDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String uf;
}
