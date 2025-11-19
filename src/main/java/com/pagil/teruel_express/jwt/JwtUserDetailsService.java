package com.pagil.teruel_express.jwt;

import com.pagil.teruel_express.exception.UsernameTypeException;
import com.pagil.teruel_express.model.dto.AccountInfoDTO;
import com.pagil.teruel_express.model.dto.HomePageDto;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.entity.PessoaFisica;
import com.pagil.teruel_express.model.entity.PessoaJuridica;
import com.pagil.teruel_express.model.entity.Role;
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
            Role role = fisicaService.buscarRolePorUsername(username);
            return jwtUtils.createToken(username, role.name().substring("ROLE_".length()));
        } else if (username.length() == 14) {
            Role role = juridicaService.buscarRolePorUsername(username);
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

    public HomePageDto getNomeTipoPessoaLogado(String username) {
        Pessoa pessoa = getPessoaLogada(username);
        HomePageDto dto = new HomePageDto();
        if(pessoa instanceof PessoaFisica){
            dto.setNome(((PessoaFisica) pessoa).getNome());
            dto.setTipoConta("Pessoa Física");
            return dto;
        } else if(pessoa instanceof PessoaJuridica){
            dto.setNome(((PessoaJuridica) pessoa).getNomeFantasia());
            dto.setTipoConta("Pessoa Jurídica");
            return dto;
        } else {
            throw new UsernameTypeException("Username não é nem CPF nem CNPJ!");
        }
    }

    public AccountInfoDTO getAccountInfo(String username) {
        Pessoa pessoa = getPessoaLogada(username);
        AccountInfoDTO dto = new AccountInfoDTO();
        dto.setPhone(pessoa.getTelefone());
        dto.setEmail(pessoa.getEmail());
        if(pessoa instanceof PessoaFisica){
            dto.setName(((PessoaFisica) pessoa).getNome());
            dto.setAccountType("Pessoa Física");
            dto.setCpfCnpj(((PessoaFisica) pessoa).getCpf());
            return dto;
        } else if(pessoa instanceof PessoaJuridica){
            dto.setName(((PessoaJuridica) pessoa).getNomeFantasia());
            dto.setAccountType("Pessoa Jurídica");
            dto.setCpfCnpj(((PessoaJuridica) pessoa).getCnpj());
            return dto;
        } else {
            throw new UsernameTypeException("Username não é nem CPF nem CNPJ!");
        }
    }
}