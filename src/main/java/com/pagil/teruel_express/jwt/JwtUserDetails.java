package com.pagil.teruel_express.jwt;

import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.entity.PessoaFisica;
import com.pagil.teruel_express.model.entity.PessoaJuridica;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails extends User {

    private Pessoa pessoa;

    public JwtUserDetails(PessoaFisica pessoa) {
        super(pessoa.getCpf(), pessoa.getSenha(), AuthorityUtils.createAuthorityList(pessoa.getRole().name()));
        this.pessoa = pessoa;
    }

    public JwtUserDetails(PessoaJuridica pessoa) {
        super(pessoa.getCnpj(), pessoa.getSenha(), AuthorityUtils.createAuthorityList(pessoa.getRole().name()));
        this.pessoa = pessoa;
    }

    public Long getId() {
        return this.pessoa.getId();
    }

    public String getRole() {
        return this.pessoa.getRole().name();
    }
}
