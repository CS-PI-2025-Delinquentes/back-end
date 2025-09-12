package com.pagil.teruel_express.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AvaliacaoUpdateDTO {

    @NotBlank
    @Min(1)
    @Max(5)
    private Integer nota;

    private String descricao;
}