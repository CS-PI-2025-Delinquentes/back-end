package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.Endereco;
import com.pagil.teruel_express.model.entity.Pacote;
import lombok.Data;

import java.util.List;

@Data
public class OrcamentoDTO {

    private Endereco origem;
    private Endereco destino;
    private List<Pacote> pacotes;

}
