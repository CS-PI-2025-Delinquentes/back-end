package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.entity.Avaliacao;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.dto.AvaliacaoUpdateDTO;
import com.pagil.teruel_express.repository.AvaliacaoRepository;
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
    AvaliacaoRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertAvaliacao() {
        Pessoa pessoa = Mockito.mock(Pessoa.class);
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(5);
        avaliacao.setDescricao("Ótimo serviço!");
        avaliacao.setPessoa(pessoa);

        Mockito.when(repository.save(avaliacao)).thenReturn(avaliacao);

        Avaliacao saved = service.insert(avaliacao);
        assertEquals(5, saved.getNota());
        assertEquals("Ótimo serviço!", saved.getDescricao());
        assertEquals(pessoa, saved.getPessoa());
    }

    @Test
    public void testFindByIdSuccess() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(4);
        avaliacao.setDescricao("Bom!");
        avaliacao.setPessoa(Mockito.mock(Pessoa.class));
        avaliacao.setId(1L);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(avaliacao));

        Avaliacao found = service.findById(1L);
        assertEquals(4, found.getNota());
        assertEquals("Bom!", found.getDescricao());
        assertEquals(1L, found.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        Mockito.when(repository.findById(2L)).thenReturn(Optional.empty());
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

        Mockito.when(repository.findById(3L)).thenReturn(Optional.of(avaliacao));
        Mockito.when(repository.save(avaliacao)).thenReturn(avaliacao);

        Avaliacao updated = service.update(3L, updateDTO);
        assertEquals(5, updated.getNota());
        assertEquals("Excelente!", updated.getDescricao());
    }

    @Test
    public void testDeleteAvaliacao() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(4L);
        Mockito.when(repository.findById(4L)).thenReturn(Optional.of(avaliacao));
        Mockito.doNothing().when(repository).delete(avaliacao);
        assertDoesNotThrow(() -> service.delete(4L));
    }
}
