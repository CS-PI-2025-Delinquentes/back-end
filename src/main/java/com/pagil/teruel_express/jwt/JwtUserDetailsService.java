package com.pagil.teruel_express.jwt;

import com.pagil.teruel_express.exception.UsernameTypeException;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.entity.PessoaFisica;
import com.pagil.teruel_express.model.entity.PessoaJuridica;
import com.pagil.teruel_express.service.PessoaFisicaService;
import com.pagil.teruel_express.service.PessoaJuridicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final PessoaFisicaService fisicaService;
    private final PessoaJuridicaService juridicaService;
    private final JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.length() == 11) {
            PessoaFisica pessoa = fisicaService.buscarPorCpf(username);
            return new JwtUserDetails(pessoa);
        } else if (username.length() == 14) {
            PessoaJuridica pessoa = juridicaService.buscarPorCnpj(username);
            return new JwtUserDetails(pessoa);
        }
        else {
            throw new UsernameTypeException("Username não é nem CPF nem CNPJ!");
        }
    }

    public JwtToken getTokenAuthenticated(String username) {
        if (username.length() == 11) {
            Pessoa.Role role = fisicaService.buscarRolePorUsername(username);
            return jwtUtils.createToken(username, role.name().substring("ROLE_".length()));
        } else if (username.length() == 14) {
            Pessoa.Role role = juridicaService.buscarRolePorUsername(username);
            return jwtUtils.createToken(username, role.name().substring("ROLE_".length()));
        }
        else {
            throw new UsernameTypeException("Username não é nem CPF nem CNPJ!");
        }
    }

    public Pessoa getPessoaLogada(String username) {
        if (username.length() == 11) {
            return fisicaService.buscarPorCpf(username);
        } else if (username.length() == 14) {
            return juridicaService.buscarPorCnpj(username);
        }
        else {
            throw new UsernameTypeException("Username não é nem CPF nem CNPJ!");
        }
    }

    public String getNomeLogado(String username) {
        Pessoa pessoa = getPessoaLogada(username);
        if(pessoa instanceof PessoaFisica){
            return ((PessoaFisica) pessoa).getNome();
        } else if(pessoa instanceof PessoaJuridica){
            return ((PessoaJuridica) pessoa).getNomeFantasia();
        } else {
            throw new UsernameTypeException("Username não é nem CPF nem CNPJ!");
        }
    }
}