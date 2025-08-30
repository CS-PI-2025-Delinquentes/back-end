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
}