package com.pagil.teruel_express.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pagil.teruel_express.model.entity.TipoPacote;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PacoteRequestDTO {

    @JsonProperty("width")
    private Double largura = 0.0;
    @JsonProperty("height")
    private Double altura = 0.0;
    @JsonProperty("length")
    private Double comprimento = 0.0;
    @JsonProperty("weight")
    private Double peso = 0.0;
    @NotNull
    @JsonProperty("amount")
    private Integer quantidade;
    @NotNull
    @JsonProperty("loadType")
    private TipoPacote tipo;
}
