package com.pagil.teruel_express.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.xml.stream.XMLInputFactory;

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

    @ManyToOne
    @JoinColumn(nullable = false, name = "pessoa_id")
    private Pessoa pessoa;
}