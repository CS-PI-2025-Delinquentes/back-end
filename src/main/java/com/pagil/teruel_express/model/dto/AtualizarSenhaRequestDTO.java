package com.pagil.teruel_express.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AtualizarSenhaRequestDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String codigo;
    @NotBlank
    private String novaSenha;
}