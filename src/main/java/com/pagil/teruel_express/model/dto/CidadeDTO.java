package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.StatusRota;
import lombok.Data;

@Data
public class CidadeDTO {
    private String nome;
    private Long estadoId;
    private StatusRota status;
}