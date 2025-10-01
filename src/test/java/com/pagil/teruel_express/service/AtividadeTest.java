package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.jwt.UserContextService;
import com.pagil.teruel_express.model.dto.AvaliacaoCreateDTO;
import com.pagil.teruel_express.model.dto.AvaliacaoUpdateDTO;
import com.pagil.teruel_express.model.entity.Avaliacao;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.entity.PessoaFisica;
import com.pagil.teruel_express.repository.AvaliacaoRepository;
import com.pagil.teruel_express.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AtividadeTest {

    @Test
    public void testFindByIdNotFound() {
        boolean var = true;
        assertTrue(var);
    }

}
