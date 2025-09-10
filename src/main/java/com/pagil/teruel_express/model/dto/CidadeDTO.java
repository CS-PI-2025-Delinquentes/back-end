package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.StatusRota;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CidadeDTO {
    @NotNull
    private String nome;

    @NotNull
    private Long estadoId;

    @NotNull
    private StatusRota status;
}