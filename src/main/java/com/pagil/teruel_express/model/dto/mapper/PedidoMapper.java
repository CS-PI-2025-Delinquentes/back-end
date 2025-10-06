package com.pagil.teruel_express.model.dto.mapper;

import com.pagil.teruel_express.exception.UsernameTypeException;
import com.pagil.teruel_express.model.dto.*;
import com.pagil.teruel_express.model.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(pedido, PedidoAdminDTO.class);
    }

    public static OrcamentoDetalhesDTO toDetalhes(Pedido pedido, List<Pacote> pacotes) {
        log.info("Pedido {}", pedido.toString());
        EnderecoDTO origemDto = EnderecoMapper.toDto(pedido.getOrigem());
        EnderecoDTO destinoDto = EnderecoMapper.toDto(pedido.getDestino());
        log.info("origem {}", origemDto);
        log.info("destino {}", destinoDto);
        Pessoa pessoa = pedido.getPessoa();
        String nomeCliente;
        if(pessoa instanceof PessoaFisica){
            nomeCliente = ((PessoaFisica) pessoa).getNome();
        } else if(pessoa instanceof PessoaJuridica) {
            nomeCliente = ((PessoaJuridica) pessoa).getNomeFantasia();
        } else throw new UsernameTypeException("Pessoa não identificada");
        log.info("nome {}", nomeCliente);
        PropertyMap<Pedido, OrcamentoDetalhesDTO> props = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setOrigem(origemDto);
                map().setDestino(destinoDto);
                map().setCliente(nomeCliente);
                map().setPacotes(toListPacoteDto(pacotes));
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        OrcamentoDetalhesDTO dto = mapper.map(pedido, OrcamentoDetalhesDTO.class);
        log.info("dto {}", dto);
        return mapper.map(pedido, OrcamentoDetalhesDTO.class);
    }

    public static List<PedidoAdminDTO> toListAdminDto(List<Pedido> pedidos){
        return pedidos.stream().map(pedido -> toAdminDto(pedido)).collect(Collectors.toList());
    }

    public static Page<PedidoAdminDTO> toPageAdminDto(Page<Pedido> pedidos){
        return pedidos.map(pedido -> toAdminDto(pedido));
    }

    public static PacoteResponseDTO toPacoteDto(Pacote pacote){
        return new ModelMapper().map(pacote, PacoteResponseDTO.class);
    }

    public static List<PacoteResponseDTO> toListPacoteDto(List<Pacote> pacotes){
        return pacotes.stream().map(pacote -> toPacoteDto(pacote)).collect(Collectors.toList());
    }
}
