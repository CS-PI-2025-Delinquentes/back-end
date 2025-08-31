package com.pagil.teruel_express.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {

    @NotBlank
    @Size(min = 11, max = 14)
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;
    @NotBlank
    @Size(min = 8)
    @JsonProperty("password")
    private String senha;

}
