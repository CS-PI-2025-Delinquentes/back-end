package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.model.dto.OrcamentoDTO;
import com.pagil.teruel_express.model.dto.mapper.PageableMapper;
import com.pagil.teruel_express.model.dto.mapper.PedidoMapper;
import com.pagil.teruel_express.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacoesController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/clientes")
    public ResponseEntity<?> findAllClient(Pageable pageable) {
        return ResponseEntity.ok(
                PageableMapper.toDto(
                        PedidoMapper.toPageClientDto(
                                pedidoService.findAllByPessoaPaged(pageable))));
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> findAllByClient(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(
                PageableMapper.toDto(
                        PedidoMapper.toPageClientDto(
                                pedidoService.findAllByPessoaIdPaged(id, pageable))));
    }

    @GetMapping("/admin")
    public ResponseEntity<?> findAllAdmin(Pageable pageable) {
        return ResponseEntity.ok(
                PageableMapper.toDto(
                        PedidoMapper.toPageAdminDto(
                                pedidoService.findAllPaged(pageable))));
    }

    @PatchMapping("/admin/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestParam boolean aceito) {
        pedidoService.avaliarPedido(id, aceito);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<?> getDetalhes(@PathVariable Long id) {
        return ResponseEntity.ok(
                PedidoMapper.toDetalhes(
                        pedidoService.findById(id), pedidoService.findPacotesByPedidoId(id)
                ));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid OrcamentoDTO orcamento) {
        pedidoService.insert(orcamento);
        return ResponseEntity.ok().build();
    }
}
