package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.Pessoa;
import lombok.Data;

@Data
public class PessoaJuridicaUpdateDTO {
    private String nomeFantasia;
    private String email;
    private String telefone;
}