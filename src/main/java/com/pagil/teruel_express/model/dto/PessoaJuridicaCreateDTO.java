package com.pagil.teruel_express.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
@ToString
public class PessoaJuridicaCreateDTO {

    @NotBlank
    @JsonProperty("nameBusiness")
    private String nomeFantasia;

    @NotBlank
    @Size(min = 14, max = 14)
    @CNPJ
    private String cnpj;

    @NotBlank
    @Email(message = "Email inválido")
    private String email;

    @NotBlank
    @JsonProperty("phone")
    private String telefone;

    @NotBlank
    @Size(min = 8)
    @JsonProperty("password")
    private String senha;

}
