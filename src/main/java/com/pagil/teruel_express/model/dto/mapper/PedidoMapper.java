package com.pagil.teruel_express.model.dto.mapper;

import com.pagil.teruel_express.exception.UsernameTypeException;
import com.pagil.teruel_express.model.dto.PedidoAdminDTO;
import com.pagil.teruel_express.model.dto.PedidoClienteDTO;
import com.pagil.teruel_express.model.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoClienteDTO toClienteDto(Pedido pedido) {
        String origem = pedido.getOrigem().getCidade().getNome();
        String destino = pedido.getDestino().getCidade().getNome();
        PropertyMap<Pedido, PedidoClienteDTO> props = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setOrigem(origem);
                map().setDestino(destino);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(pedido, PedidoClienteDTO.class);
    }

    public static List<PedidoClienteDTO> toListClienteDto(List<Pedido> pedidos){
        return pedidos.stream().map(pedido -> toClienteDto(pedido)).collect(Collectors.toList());
    }

    public static Page<PedidoClienteDTO> toPageClientDto(Page<Pedido> pedidos){
        return pedidos.map(pedido -> toClienteDto(pedido));
    }

    public static PedidoAdminDTO toAdminDto(Pedido pedido) {
        String origem = pedido.getOrigem().getCidade().getNome();
        String destino = pedido.getDestino().getCidade().getNome();
        Pessoa pessoa = pedido.getPessoa();
        String email = pessoa.getEmail();
        String nomeCliente;
        if(pessoa instanceof PessoaFisica){
            nomeCliente = ((PessoaFisica) pessoa).getNome();
        } else if(pessoa instanceof PessoaJuridica) {
            nomeCliente = ((PessoaJuridica) pessoa).getNomeFantasia();
        } else throw new UsernameTypeException("Pessoa não identificada");

        PropertyMap<Pedido, PedidoAdminDTO> props = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setOrigem(origem);
                map().setDestino(destino);
                map().setCliente(nomeCliente);
                map().setEmail(email);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(pedido, PedidoAdminDTO.class);
    }

    public static List<PedidoAdminDTO> toListAdminDto(List<Pedido> pedidos){
        return pedidos.stream().map(pedido -> toAdminDto(pedido)).collect(Collectors.toList());
    }

    public static Page<PedidoAdminDTO> toPageAdminDto(Page<Pedido> pedidos){
        return pedidos.map(pedido -> toAdminDto(pedido));
    }

}
