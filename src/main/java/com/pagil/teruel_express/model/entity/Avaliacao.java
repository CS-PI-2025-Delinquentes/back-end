package com.pagil.teruel_express.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nota")
    private Integer nota;

    @Column(nullable = false, name = "descricao")
    private String descricao;

    @Column(nullable = false, name = "data_avaliacao")
    private LocalDate dataAvaliacao = LocalDate.now();

    @ManyToOne
    @JoinColumn(nullable = false, name = "pessoa_id")
    private Pessoa pessoa;
}