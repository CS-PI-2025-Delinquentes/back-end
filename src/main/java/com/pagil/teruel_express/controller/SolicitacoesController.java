package com.pagil.teruel_express.controller;

import com.pagil.teruel_express.model.dto.mapper.PageableMapper;
import com.pagil.teruel_express.model.dto.mapper.PedidoMapper;
import com.pagil.teruel_express.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacoesController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/client")
    public ResponseEntity<?> findAllClient(Pageable pageable) {
        return ResponseEntity.ok(
                PageableMapper.toDto(
                        PedidoMapper.toPageClientDto(
                                pedidoService.findAllByPessoaPaged(pageable))));
    }

    @GetMapping("/client/{id}")
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


}
