package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.dto.AtualizarSenhaRequestDTO;
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

import java.time.LocalDateTime;
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

    public void limparCodigosExpirados() {
        codigoRepository.deleteAll(
            codigoRepository.findAll().stream()
                .filter(Codigo::isExpired)
                .toList()
        );
    }

    public Codigo insert(String email) {
        Pessoa pessoaBank = pessoaRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Email não encontrado")
        );

        limparCodigosExpirados();

        codigoRepository.findByPessoa(pessoaBank).ifPresent(codigoRepository::delete);

        Codigo codigo = new Codigo();
        codigo.setPessoa(pessoaBank);
        codigo.setCodigo(NumberGenerator.generateNumberConfirmation());
        codigo.setCreatedAt(LocalDateTime.now());
        codigo.setExpiresAt(LocalDateTime.now().plusMinutes(15));

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
                () -> new NotFoundException("Email invalido!")
        );

        Codigo codigo = codigoRepository.findByPessoa(pessoaBank).orElseThrow(
                () -> new NotFoundException("Codigo invalido!")
        );

        espiracaoCod(codigo);

        return codigo.getCodigo().equals(codigoRecebido);
    }

    public boolean atualizarSenha(AtualizarSenhaRequestDTO atualizarSenhaRequestDTO) {
        Pessoa pessoaBank = pessoaRepository.findByEmail(atualizarSenhaRequestDTO.getEmail()).orElseThrow(
                () -> new NotFoundException("Email invalido!")
        );

        Codigo codigoBank = codigoRepository.findByPessoa(pessoaBank).orElseThrow(
                () -> new NotFoundException("Codigo invalido!")
        );

        espiracaoCod(codigoBank);

        if (!codigoBank.getCodigo().equals(atualizarSenhaRequestDTO.getCodigo())) {
            throw new NotFoundException("Código inválido.");
        }

        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        pessoaBank.setSenha(encode.encode(atualizarSenhaRequestDTO.getNovaSenha()));
        pessoaRepository.save(pessoaBank);
        deleteByPessoa(pessoaBank);

        return true;
    }

    private void espiracaoCod(Codigo codigoBank) {
        if (codigoBank.isExpired()) {
            codigoRepository.delete(codigoBank);
            throw new NotFoundException("Código expirado");
        }
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