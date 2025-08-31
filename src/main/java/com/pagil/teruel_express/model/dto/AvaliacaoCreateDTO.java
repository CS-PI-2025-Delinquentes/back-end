package com.pagil.teruel_express.model.dto;

import lombok.Data;

@Data
public class AvaliacaoCreateDTO {
    private Integer nota;
    private String descricao;
    private Long pessoaId;
}