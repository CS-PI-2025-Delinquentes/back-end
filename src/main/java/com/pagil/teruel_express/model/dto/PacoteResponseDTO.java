package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.TipoPacote;
import lombok.Data;

@Data
public class PacoteResponseDTO {

    private Long id;
    private Double largura = 0.0;
    private Double altura = 0.0;
    private Double comprimento = 0.0;
    private Double peso = 0.0;
    private Integer quantidade;
    private TipoPacote tipo;
}
