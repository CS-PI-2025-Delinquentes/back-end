package com.pagil.teruel_express.model.dto;

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
    private String rua;
    @NotBlank
    private String bairro;
    @NotBlank
    private String numero;
    @NotBlank
    private String cidade;
}
