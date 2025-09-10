package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.CityStateUniqueViolationException;
import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.dto.CidadeDTO;
import com.pagil.teruel_express.model.entity.Cidade;
import com.pagil.teruel_express.model.entity.Estado;
import com.pagil.teruel_express.model.entity.StatusRota;
import com.pagil.teruel_express.repository.CidadeRepository;
import com.pagil.teruel_express.repository.EstadoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class CidadeServiceTest {

    @Mock
    CidadeRepository cidadeRepository;

    @Mock
    EstadoRepository estadoRepository;

    @InjectMocks
    CidadeService service;

    @Test
    public void testInsertCidadeCerto() {
        Estado estado = new Estado();
        estado.setNome("Estado");
        estado.setUf("ES");
        CidadeDTO cidadeDto = new CidadeDTO();
        cidadeDto.setNome("Cidade");
        cidadeDto.setStatus(StatusRota.ATIVO);
        cidadeDto.setEstadoId(1L);
        Cidade cidade = new Cidade();
        cidade.setNome("Cidade");
        cidade.setStatus(StatusRota.ATIVO);
        cidade.setEstado(estado);

        Mockito.when(estadoRepository.findById(1L)).thenReturn(Optional.of(estado));
        Mockito.when(cidadeRepository.findByNomeAndEstado(cidadeDto.getNome(), estado)).thenReturn(Optional.empty());
        Mockito.when(cidadeRepository.save(Mockito.any(Cidade.class))).thenReturn(cidade);

        Cidade cidadeSaved = service.insert(cidadeDto);
        assertEquals(cidade.getNome(), cidadeSaved.getNome());
        assertEquals(cidade.getStatus(), cidadeSaved.getStatus());
        assertEquals(cidade.getEstado(), cidadeSaved.getEstado());
    }

    @Test
    public void testInsertCidadeErroEstadoNaoExistente() {
        CidadeDTO cidadeDto = new CidadeDTO();
        cidadeDto.setNome("Cidade");
        cidadeDto.setStatus(StatusRota.ATIVO);
        cidadeDto.setEstadoId(1L);

        Mockito.when(estadoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.insert(cidadeDto));
    }

    @Test
    public void testInsertCidadeErroCidadeJaCadastrado() {
        Estado estado = new Estado();
        estado.setNome("Estado");
        estado.setUf("ES");
        CidadeDTO cidadeDto = new CidadeDTO();
        cidadeDto.setNome("Cidade");
        cidadeDto.setStatus(StatusRota.ATIVO);
        cidadeDto.setEstadoId(1L);
        Cidade cidade = new Cidade();
        cidade.setNome("Cidade");
        cidade.setStatus(StatusRota.ATIVO);
        cidade.setEstado(estado);

        Mockito.when(estadoRepository.findById(1L)).thenReturn(Optional.of(estado));
        Mockito.when(cidadeRepository.findByNomeAndEstado(cidadeDto.getNome(), estado)).thenReturn(Optional.of(cidade));

        assertThrows(CityStateUniqueViolationException.class, () -> service.insert(cidadeDto));
    }

    @Test
    public void testUpdateCidadeCerto() {
        Estado estado = new Estado();
        estado.setNome("Estado");
        estado.setUf("ES");
        CidadeDTO cidadeDto = new CidadeDTO();
        cidadeDto.setNome("Cidade");
        cidadeDto.setStatus(StatusRota.ATIVO);
        cidadeDto.setEstadoId(1L);
        Cidade cidade = new Cidade();
        cidade.setNome("Cidade");
        cidade.setStatus(StatusRota.ATIVO);
        cidade.setEstado(estado);

        Mockito.when(estadoRepository.findById(1L)).thenReturn(Optional.of(estado));
        Mockito.when(cidadeRepository.findByNomeAndEstado(cidadeDto.getNome(), estado)).thenReturn(Optional.empty());
        Mockito.when(cidadeRepository.save(Mockito.any(Cidade.class))).thenReturn(cidade);
        Mockito.when(cidadeRepository.findById(1L)).thenReturn(Optional.of(cidade));

        Cidade cidadeSaved = service.update(1L, cidadeDto);
        assertEquals(cidade.getNome(), cidadeSaved.getNome());
        assertEquals(cidade.getStatus(), cidadeSaved.getStatus());
        assertEquals(cidade.getEstado(), cidadeSaved.getEstado());
    }

    @Test
    public void testUpdateCidadeErroEstadoNaoExistente() {
        CidadeDTO cidadeDto = new CidadeDTO();
        cidadeDto.setNome("Cidade");
        cidadeDto.setStatus(StatusRota.ATIVO);
        cidadeDto.setEstadoId(1L);

        Mockito.when(estadoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.update(1L, cidadeDto));
    }

    @Test
    public void testUpdateCidadeErroCidadeNaoEncontrada() {
        CidadeDTO cidadeDto = new CidadeDTO();
        cidadeDto.setNome("Cidade");
        cidadeDto.setStatus(StatusRota.ATIVO);
        cidadeDto.setEstadoId(1L);

        Mockito.when(cidadeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.update(1L, cidadeDto));
    }

    @Test
    public void testFindByIdCidadeCerto() {
        Estado estado = new Estado();
        Cidade cidade = new Cidade();
        cidade.setNome("Cidade");
        cidade.setStatus(StatusRota.ATIVO);
        cidade.setEstado(estado);

        Mockito.when(cidadeRepository.findById(1L)).thenReturn(Optional.of(cidade));
        Cidade cidadeSaved = service.findById(1L);

        assertEquals(cidade.getNome(), cidadeSaved.getNome());
        assertEquals(cidade.getStatus(), cidadeSaved.getStatus());
        assertEquals(cidade.getEstado(), cidadeSaved.getEstado());
    }

    @Test
    public void testFindByIdCidadeErro() {
        Mockito.when(cidadeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.findById(1L));
    }

    @Test
    public void testDeleteCidadeCerto() {
        Estado estado = new Estado();
        Cidade cidade = new Cidade();
        cidade.setNome("Cidade");
        cidade.setStatus(StatusRota.ATIVO);
        cidade.setEstado(estado);

        Mockito.when(cidadeRepository.findById(1L)).thenReturn(Optional.of(cidade));
        Mockito.doNothing().when(cidadeRepository).delete(cidade);

        assertDoesNotThrow(() -> service.delete(1L));
    }

    @Test
    public void testDeleteCidadeErro() {
        Mockito.when(cidadeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.delete(1L));
    }

    @Test
    public void testFindAllCidade() {
        Estado estado = new Estado();
        estado.setNome("Estado");
        estado.setUf("ES");

        Cidade cidade1 = new Cidade();
        cidade1.setNome("Cidade1");
        cidade1.setStatus(StatusRota.ATIVO);
        cidade1.setEstado(estado);

        Cidade cidade2 = new Cidade();
        cidade2.setNome("Cidade2");
        cidade2.setStatus(StatusRota.ATIVO);
        cidade2.setEstado(estado);

        List<Cidade> cidades = Arrays.asList(cidade1, cidade2);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Cidade> pageResult = new PageImpl<>(cidades, pageable, cidades.size());

        Mockito.when(cidadeRepository.findAll(pageable)).thenReturn(pageResult);

        Page<Cidade> result = service.findAll(pageable);

        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals(2, result.getTotalElements());
        assertEquals("Cidade1", result.getContent().get(0).getNome());
        assertEquals("Cidade2", result.getContent().get(1).getNome());
    }
}
