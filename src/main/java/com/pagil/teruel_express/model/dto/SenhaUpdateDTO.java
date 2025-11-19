package com.pagil.teruel_express.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data @ToString
public class SenhaUpdateDTO {

    @NotBlank
    private String currentPassword;

    @NotBlank
    @Size(min = 8)
    private String password;
}
