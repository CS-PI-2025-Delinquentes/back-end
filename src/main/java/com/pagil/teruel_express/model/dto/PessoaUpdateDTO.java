package com.pagil.teruel_express.model.dto;

import lombok.Data;
import lombok.ToString;

@Data @ToString
public class PessoaUpdateDTO {

    private String name;
    private String email;
    private String phone;
}
