package com.pagil.teruel_express.model.dto.mapper;

import com.pagil.teruel_express.model.dto.EnderecoDTO;
import com.pagil.teruel_express.model.dto.OrcamentoDTO;
import com.pagil.teruel_express.model.dto.PedidoClienteDTO;
import com.pagil.teruel_express.model.entity.Cidade;
import com.pagil.teruel_express.model.entity.Endereco;
import com.pagil.teruel_express.model.entity.Pedido;
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

}
