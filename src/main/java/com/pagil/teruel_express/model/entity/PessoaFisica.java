package com.pagil.teruel_express.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pessoa_fisica")
public class PessoaFisica extends Pessoa {
    @Column(nullable = false, name = "nome")
    private String nome;
    @Column(unique=true, nullable=false, length=11, name = "cpf")
    private String cpf;

    public PessoaFisica() {
        super();
    }

    public PessoaFisica(Long id, String email, String telefone, String senha, Role role, String nome, String cpf) {
        super(id, email, telefone, senha, role);
        this.nome = nome;
        this.cpf = cpf;
    }
}
