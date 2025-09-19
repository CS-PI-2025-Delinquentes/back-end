package com.pagil.teruel_express.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "pacote")
public class Pacote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "largura")
    private Double largura = 0.0;

    @Column(nullable = false, name = "altura")
    private Double altura = 0.0;

    @Column(nullable = false, name = "comprimento")
    private Double comprimento = 0.0;

    @Column(nullable = false, name = "peso")
    private Double peso = 0.0;

    @Column(nullable = false, name = "quantidade")
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo")
    private TipoPacote tipo;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;


}
