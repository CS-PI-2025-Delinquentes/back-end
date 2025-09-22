package com.pagil.teruel_express.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "telefone")
    private String telefone;

    @Column(nullable = false, name = "senha")
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role")
    private Role role = Role.ROLE_CLIENT;

    public enum Role {
        ROLE_CLIENT, ROLE_ADMIN, ROLE_DEV
    }

    public Pessoa() {}

    public Pessoa(Long id, String email, String telefone, String senha, Role role) {
        this.id = id;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.role = role;
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }
}
