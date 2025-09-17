package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.jwt.UserContextService;
import com.pagil.teruel_express.model.dto.AvaliacaoCreateDTO;
import com.pagil.teruel_express.model.entity.Avaliacao;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.dto.AvaliacaoUpdateDTO;
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

public class AvaliacaoServiceTest {

    @InjectMocks
    AvaliacaoService service;

    @Mock
    AvaliacaoRepository avaliacaoRepository;

    @Mock
    PessoaRepository pessoaRepository;

    @Mock
    UserContextService userContextService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertAvaliacao() {
        AvaliacaoCreateDTO dto = new AvaliacaoCreateDTO();
        dto.setNota(5);
        dto.setDescricao("Ótimo serviço!");

        PessoaFisica pessoaFisica = Mockito.mock(PessoaFisica.class);
        Mockito.when(pessoaFisica.getNome()).thenReturn("Arthur");

        Mockito.when(userContextService.getCurrentUserId()).thenReturn(1L);
        Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoaFisica));

        Avaliacao avaliacaoSalva = new Avaliacao();
        avaliacaoSalva.setNota(dto.getNota());
        avaliacaoSalva.setDescricao(dto.getDescricao());
        avaliacaoSalva.setPessoa(pessoaFisica);

        Mockito.when(avaliacaoRepository.save(Mockito.any(Avaliacao.class))).thenReturn(avaliacaoSalva);

        var saved = service.insert(dto);

        assertEquals(5, saved.getNota());
        assertEquals("Ótimo serviço!", saved.getDescricao());
        assertEquals("Arthur", saved.getNomeAvaliador());
    }

    @Test
    public void testFindByIdSuccess() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(4);
        avaliacao.setDescricao("Bom!");
        avaliacao.setPessoa(Mockito.mock(Pessoa.class));
        avaliacao.setId(1L);

        Mockito.when(avaliacaoRepository.findById(1L)).thenReturn(Optional.of(avaliacao));

        Avaliacao found = service.findById(1L);
        assertEquals(4, found.getNota());
        assertEquals("Bom!", found.getDescricao());
        assertEquals(1L, found.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        Mockito.when(avaliacaoRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.findById(2L));
    }

    @Test
    public void testUpdateAvaliacao() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(3L);
        avaliacao.setNota(2);
        avaliacao.setDescricao("Ruim");

        AvaliacaoUpdateDTO updateDTO = new AvaliacaoUpdateDTO();
        updateDTO.setNota(5);
        updateDTO.setDescricao("Excelente!");

        Mockito.when(avaliacaoRepository.findById(3L)).thenReturn(Optional.of(avaliacao));
        Mockito.when(avaliacaoRepository.save(avaliacao)).thenReturn(avaliacao);

        Avaliacao updated = service.update(3L, updateDTO);
        assertEquals(5, updated.getNota());
        assertEquals("Excelente!", updated.getDescricao());
    }

    @Test
    public void testDeleteAvaliacao() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(4L);
        Mockito.when(avaliacaoRepository.findById(4L)).thenReturn(Optional.of(avaliacao));
        Mockito.doNothing().when(avaliacaoRepository).delete(avaliacao);
        assertDoesNotThrow(() -> service.delete(4L));
    }
}
