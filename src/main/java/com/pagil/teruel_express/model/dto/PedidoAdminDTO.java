package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.StatusPedido;
import lombok.Data;

@Data
public class PedidoAdminDTO {

    private Long id;
    private StatusPedido status;
    private String origem;
    private String destino;
    private String cliente;

}
