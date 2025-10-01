package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.Endereco;
import com.pagil.teruel_express.model.entity.Pacote;
import com.pagil.teruel_express.model.entity.StatusPedido;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrcamentoDetalhesDTO {

    private String cliente;
    private StatusPedido status;
    private LocalDate dataPedido;
    private EnderecoDTO origem;
    private EnderecoDTO destino;
    private List<Pacote> pacotes;
}
