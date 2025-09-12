package com.pagil.teruel_express.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private StatusPedido status;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "endereco_origem_id")
    private Endereco origem;

    @ManyToOne
    @JoinColumn(name = "endereco_destino_id")
    private Endereco destino;

}
