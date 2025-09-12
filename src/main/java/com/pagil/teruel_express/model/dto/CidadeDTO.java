package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.StatusRota;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CidadeDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private Long estadoId;

    @NotBlank
    private StatusRota status;
}