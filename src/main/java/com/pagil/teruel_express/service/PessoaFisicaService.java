package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.BusinessLogicException;
import com.pagil.teruel_express.exception.EmailCpfUniqueViolationException;
import com.pagil.teruel_express.exception.InvalidValuesException;
import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.jwt.JwtUserDetailsService;
import com.pagil.teruel_express.jwt.UserContextService;
import com.pagil.teruel_express.model.dto.AccountInfoDTO;
import com.pagil.teruel_express.model.dto.PessoaFisicaCreateDTO;
import com.pagil.teruel_express.model.dto.PessoaUpdateDTO;
import com.pagil.teruel_express.model.dto.SenhaUpdateDTO;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.entity.PessoaFisica;
import com.pagil.teruel_express.model.entity.Role;
import com.pagil.teruel_express.repository.PessoaFisicaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import java.util.Optional;

@Service
@Slf4j
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private EmailService emailService;

    public PessoaFisica insert(PessoaFisicaCreateDTO pessoaFisicaCreateDTO) {
        Optional<PessoaFisica> pessoaFisicaBank = pessoaFisicaRepository.findByEmailOrCpf(pessoaFisicaCreateDTO.getEmail(), pessoaFisicaCreateDTO.getCpf());
        if (pessoaFisicaBank.isPresent()) {
            throw new EmailCpfUniqueViolationException("Email ou CPF já cadastrado");
        }

        PessoaFisica pessoaFisicaNova = getPessoaFisica(pessoaFisicaCreateDTO);

        enviarEmailSucesso(pessoaFisicaNova);
        return pessoaFisicaRepository.save(pessoaFisicaNova);
    }

    private static PessoaFisica getPessoaFisica(PessoaFisicaCreateDTO pessoaFisicaCreateDTO) {
        PessoaFisica pessoaFisicaNova = new PessoaFisica();
        pessoaFisicaNova.setNome(pessoaFisicaCreateDTO.getNome());
        pessoaFisicaNova.setEmail(pessoaFisicaCreateDTO.getEmail());
        pessoaFisicaNova.setCpf(pessoaFisicaCreateDTO.getCpf());
        pessoaFisicaNova.setTelefone(pessoaFisicaCreateDTO.getTelefone());

        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        pessoaFisicaNova.setSenha(encode.encode(pessoaFisicaCreateDTO.getSenha()));
        return pessoaFisicaNova;
    }

    private void enviarEmailSucesso(PessoaFisica pessoaFisica) {
        Context context = new Context();
        context.setVariable("nome", pessoaFisica.getNome());
        emailService.emailTemplate(pessoaFisica.getEmail(), "Cadastro Teruel Express", context, "cadastroTeruelExpress");
    }

    public PessoaFisica update(Long id, PessoaUpdateDTO pessoaUpdateDTO) {
        PessoaFisica person = findById(id);

        if(pessoaUpdateDTO.getName() != null) person.setNome(pessoaUpdateDTO.getName());
        if(pessoaUpdateDTO.getEmail() != null) person.setEmail(pessoaUpdateDTO.getEmail());
        if(pessoaUpdateDTO.getPhone() != null) person.setTelefone(pessoaUpdateDTO.getPhone());

        return pessoaFisicaRepository.save(person);
    }

    public void delete(Long id) {
        PessoaFisica pessoafisicaBank = findById(id);
        pessoaFisicaRepository.deleteById(id);
    }

    public PessoaFisica findById(Long id) {
        return pessoaFisicaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Pessoa física com id %d não encontrado", id))
        );
    }

    public Page<PessoaFisica> findAll(Pageable pageable) {
        return pessoaFisicaRepository.findAll(pageable);
    }

    public PessoaFisica buscarPorCpf(String cpf) {
        return pessoaFisicaRepository.findByCpf(cpf).orElseThrow(
                () -> new NotFoundException("CPF não encontrado")
        );
    }

    public void updateSenha(Long id, SenhaUpdateDTO dto) {
        if(dto.getCurrentPassword().equals(dto.getPassword()))
            throw new BusinessLogicException("Senha nova não deve ser igual antiga");
        PessoaFisica person = findById(id);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(dto.getCurrentPassword(), person.getSenha())) {
            throw new InvalidValuesException("Senha anterior incorreta");
        } else {
            person.setSenha(encoder.encode(dto.getPassword()));
            pessoaFisicaRepository.save(person);
        }
    }

    public Role buscarRolePorUsername(String cpf) {
        return pessoaFisicaRepository.findRoleByCpf(cpf);
    }
}