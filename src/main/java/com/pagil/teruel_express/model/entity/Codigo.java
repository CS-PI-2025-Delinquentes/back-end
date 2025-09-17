package com.pagil.teruel_express.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Optional;

@Entity
@Data
@Table(name = "codigo")
public class Codigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Codigo(Long id, String codigo, Pessoa pessoa) {
        this.id = id;
        this.codigo = codigo;
        this.pessoa = pessoa;
    }

    public Codigo() {
    }
}
