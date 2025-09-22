package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Codigo insert(String email){
        Codigo codigo = new Codigo();

        Pessoa pessoaBank = pessoaRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Email não econtrado")
        );

        codigo.setPessoa(pessoaBank);
        codigo.setCodigo(NumberGenerator.generateNumberConfirmation());

        enviarCodigo(codigo.getCodigo(), email);

        return codigoRepository.save(codigo);
    }

    public void enviarCodigo(String codigo, String email) {
        Context context = new Context();
        context.setVariable("codigo", codigo);
        emailService.emailTemplate(email, "Codigo recuperação senha", context, "cadastroTeruelExpress");
    }

    public boolean validar(String email, String codigoRecebido) {
        Pessoa pessoaBank = pessoaRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Email não econtrado")
        );
        Codigo codigo = codigoRepository.findByPessoa(pessoaBank).orElseThrow(
                () -> new NotFoundException("Codigo não econtrado para pessoa")
        );
    }
}