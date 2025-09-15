package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.StatusPedido;
import lombok.Data;

@Data
public class PedidoClienteDTO {

    private Long id;

    private StatusPedido status;

    private String origem;

    private String destino;
}
