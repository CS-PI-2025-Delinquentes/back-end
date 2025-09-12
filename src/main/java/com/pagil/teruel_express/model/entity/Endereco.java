package com.pagil.teruel_express.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 8, name = "cep")
    private String cep;

    @Column(nullable = false, name = "rua")
    private String rua;

    @Column(nullable = false, length = 100, name = "bairro")
    private String bairro;

    @Column(nullable = false, length = 10, name = "numero")
    private String numero;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;
}
