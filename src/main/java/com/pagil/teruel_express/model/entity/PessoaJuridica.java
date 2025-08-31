package com.pagil.teruel_express.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class PessoaJuridica extends Pessoa {

    private String nome_fantasia;
    @Column(unique=true, nullable=false, length=14)
    private String cnpj;

    public PessoaJuridica() {
        super();
    }

    public PessoaJuridica(Long id, String email, String telefone, String senha, Role role, String nome_fantasia, String cnpj) {
        super(id, email, telefone, senha, role);
        this.nome_fantasia = nome_fantasia;
        this.cnpj = cnpj;
    }
}
