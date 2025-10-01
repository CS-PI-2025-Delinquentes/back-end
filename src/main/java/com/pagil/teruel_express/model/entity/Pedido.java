package com.pagil.teruel_express.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private StatusPedido status = StatusPedido.PENDENTE;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @Column(nullable = false, name = "data_solicitacao")
    private LocalDate dataPedido = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "endereco_origem_id")
    private Endereco origem;

    @ManyToOne
    @JoinColumn(name = "endereco_destino_id")
    private Endereco destino;

}
