package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.entity.PessoaFisica;
import com.pagil.teruel_express.model.entity.PessoaJuridica;
import com.pagil.teruel_express.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pagil.teruel_express.model.entity.Codigo;
import com.pagil.teruel_express.repository.CodigoRepository;
import com.pagil.teruel_express.utils.NumberGenerator;
import org.thymeleaf.context.Context;

import java.util.Optional;

@Service
public class CodigoService {

    @Autowired
    private CodigoRepository codigoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EmailService emailService;

    private String getNomePessoa(Pessoa pessoa) {
        if (pessoa instanceof PessoaFisica) {
            return ((PessoaFisica) pessoa).getNome();
        } else if (pessoa instanceof PessoaJuridica) {
            return ((PessoaJuridica) pessoa).getNomeFantasia();
        }
        return "Usuário";
    }

    public Codigo insert(String email) {
        Pessoa pessoaBank = pessoaRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Email não encontrado")
        );

        codigoRepository.findByPessoa(pessoaBank).ifPresent(codigoRepository::delete);

        Codigo codigo = new Codigo();
        codigo.setPessoa(pessoaBank);
        codigo.setCodigo(NumberGenerator.generateNumberConfirmation());

        String nome = getNomePessoa(pessoaBank);
        enviarCodigo(codigo.getCodigo(), email, nome);

        return codigoRepository.save(codigo);
    }

    public void enviarCodigo(String codigo, String email, String nome) {
        Context context = new Context();
        context.setVariable("codigo", codigo);
        context.setVariable("nome", nome);
        emailService.emailTemplate(email, "Codigo recuperação de senha", context, "recuperarSenhaTeruelExpress");
    }

    public boolean validar(String email, String codigoRecebido) {
        Pessoa pessoaBank = pessoaRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Email não encontrado")
        );
        Codigo codigo = codigoRepository.findByPessoa(pessoaBank).orElseThrow(
                () -> new NotFoundException("Codigo não encontrado para pessoa")
        );
        return codigo.getCodigo().equals(codigoRecebido);
    }

    public boolean atualizarSenha(String email, String codigo, String novaSenha) {
        Pessoa pessoaBank = pessoaRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Email não encontrado")
        );

        Codigo codigoBank = codigoRepository.findByPessoa(pessoaBank).orElseThrow(
                () -> new NotFoundException("Codigo não encontrado para pessoa")
        );

        if (!codigoBank.getCodigo().equals(codigo)) {
            throw new NotFoundException("Código inválido.");
        }

        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        pessoaBank.setSenha(encode.encode(novaSenha));
        pessoaRepository.save(pessoaBank);
        deleteByPessoa(pessoaBank);

        return true;
    }

    public void deleteByPessoa(Pessoa pessoa) {
        Optional<Codigo> codigoOpt = codigoRepository.findByPessoa(pessoa);
        if (codigoOpt.isPresent()) {
            codigoRepository.delete(codigoOpt.get());
        } else {
            throw new NotFoundException("Código não encontrado para a pessoa fornecida.");
        }
    }
}