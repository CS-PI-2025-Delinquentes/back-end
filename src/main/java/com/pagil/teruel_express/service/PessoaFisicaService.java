package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.EmailCpfUniqueViolationException;
import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.dto.PessoaFisicaCreateDTO;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.dto.PessoaFisicaUpdateDTO;
import com.pagil.teruel_express.model.entity.PessoaFisica;
import com.pagil.teruel_express.model.entity.Role;
import com.pagil.teruel_express.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import java.util.Optional;

@Service
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

    public PessoaFisica update(Long id, PessoaFisicaUpdateDTO pessoaFisicaUpdateDTO) {
        PessoaFisica pessoaFisicaBank = findById(id);

        pessoaFisicaBank.setNome(pessoaFisicaUpdateDTO.getNome());
        pessoaFisicaBank.setEmail(pessoaFisicaUpdateDTO.getEmail());
        pessoaFisicaBank.setTelefone(pessoaFisicaUpdateDTO.getTelefone());

        return pessoaFisicaRepository.save(pessoaFisicaBank);
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

    public Role buscarRolePorUsername(String cpf) {
        return pessoaFisicaRepository.findRoleByCpf(cpf);
    }
}