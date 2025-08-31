package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.Pessoa;
import lombok.Data;

@Data
public class PessoaFisicaUpdateDTO {
    private String nome;
    private String email;
    private String telefone;
}
