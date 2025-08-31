package com.pagil.teruel_express.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EstadoDTO {

    @NotNull
    private String nome;

    @NotNull
    private String uf;
}
