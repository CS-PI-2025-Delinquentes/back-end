package com.pagil.teruel_express.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pessoa_juridica")
public class PessoaJuridica extends Pessoa {

    @Column(nullable = false, name = "nome_fantasia")
    private String nomeFantasia;

    @Column(unique=true, nullable=false, length=14)
    private String cnpj;

    public PessoaJuridica() {
        super();
    }

    public PessoaJuridica(Long id, String email, String telefone, String senha, Role role, String nomeFantasia, String cnpj) {
        super(id, email, telefone, senha, role);
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
    }
}
