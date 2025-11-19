package com.pagil.teruel_express.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrcamentoDTO {
    @Valid
    @NotNull
    @JsonProperty("addressOrigin")
    private EnderecoDTO origem;

    @Valid
    @NotNull
    @JsonProperty("addressDestiny")
    private EnderecoDTO destino;

    @Valid
    @JsonProperty("observation")
    private String observacao;

    @Valid
    @NotEmpty
    @JsonProperty("listPackages")
    private List<PacoteRequestDTO> pacotes;

}