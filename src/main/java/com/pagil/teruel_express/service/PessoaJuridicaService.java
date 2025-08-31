package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.EmailCnpjUniqueViolationException;
import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.dto.PessoaJuridicaUpdateDTO;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.entity.PessoaJuridica;
import com.pagil.teruel_express.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.Optional;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    private EmailService emailService;

    public PessoaJuridica insert(PessoaJuridica pessoaJuridica) {
        Optional<PessoaJuridica> pessoaJuridicaBank = pessoaJuridicaRepository.findByEmailOrCnpj(pessoaJuridica.getEmail(), pessoaJuridica.getCnpj());
        if (pessoaJuridicaBank.isPresent()) {
            throw new EmailCnpjUniqueViolationException("Email ou CNPJ já cadastrado");
        }

        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        pessoaJuridica.setSenha(encode.encode(pessoaJuridica.getSenha()));

        enviarEmailSucesso(pessoaJuridica);
        return pessoaJuridicaRepository.save(pessoaJuridica);
    }

    private void enviarEmailSucesso(PessoaJuridica pessoaJuridica) {
        Context context = new Context();
        context.setVariable("nome", pessoaJuridica.getNome_fantasia());
        emailService.emailTemplate(pessoaJuridica.getEmail(), "Cadastro Teruel Express", context, "cadastroTeruelExpress");
    }

    public PessoaJuridica update(Long id, PessoaJuridicaUpdateDTO pessoaJuridicaUpdateDTO) {
        PessoaJuridica pessoaJuridicaBank = findById(id);

        pessoaJuridicaBank.setNome_fantasia(pessoaJuridicaUpdateDTO.getNomeFantasia());
        pessoaJuridicaBank.setEmail(pessoaJuridicaUpdateDTO.getEmail());
        pessoaJuridicaBank.setTelefone(pessoaJuridicaUpdateDTO.getTelefone());

        return pessoaJuridicaRepository.save(pessoaJuridicaBank);
    }

    public void delete(Long id) {
        PessoaJuridica pessoaJuridicaBank = findById(id);
        pessoaJuridicaRepository.deleteById(id);
    }

    public PessoaJuridica findById(Long id) {
        PessoaJuridica pessoaJuridicaBank = pessoaJuridicaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Pessoa Juridica com id %d não encontrada", id))
        );
        return pessoaJuridicaBank;
    }

    public Page<PessoaJuridica> findAll(Pageable pageable) {
        return pessoaJuridicaRepository.findAll(pageable);
    }

    public PessoaJuridica buscarPorCnpj(String cnpj) {
        return pessoaJuridicaRepository.findByCnpj(cnpj).orElseThrow(
                () -> new NotFoundException("CNPJ não encontrado")
        );
    }

    public Pessoa.Role buscarRolePorUsername(String cnpj) {
        return pessoaJuridicaRepository.findRoleByCnpj(cnpj);
    }
}