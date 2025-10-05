package com.pagil.teruel_express.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "codigo")
public class Codigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Codigo(Long id, String codigo, Pessoa pessoa) {
        this.id = id;
        this.codigo = codigo;
        this.pessoa = pessoa;
    }

    public Codigo() {
    }
}
