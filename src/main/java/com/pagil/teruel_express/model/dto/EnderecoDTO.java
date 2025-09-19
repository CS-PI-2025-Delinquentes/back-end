package com.pagil.teruel_express.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EnderecoDTO {

    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;

    @NotBlank
    @JsonProperty("street")
    private String rua;

    @NotBlank
    @JsonProperty("neighborhood")
    private String bairro;

    @NotBlank
    @JsonProperty("number")
    private String numero;

    @NotBlank
    @JsonProperty("city")
    private String cidade;

}
