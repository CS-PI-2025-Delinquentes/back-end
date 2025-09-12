package com.pagil.teruel_express.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

@Data
@ToString
public class PessoaFisicaCreateDTO {

    @NotBlank
    @JsonProperty("namePerson")
    private String nome;

    @NotBlank
    @Size(min = 11, max = 11)
    @CPF
    private String cpf;

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