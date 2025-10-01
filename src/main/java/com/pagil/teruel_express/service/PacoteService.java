package com.pagil.teruel_express.service;

import com.pagil.teruel_express.exception.NotFoundException;
import com.pagil.teruel_express.model.dto.EnderecoDTO;
import com.pagil.teruel_express.model.entity.*;
import com.pagil.teruel_express.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PacoteService {

    @Autowired
    private PacoteRepository pacoteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @Autowired
    private EnderecoService enderecoService;

    public Pacote teste(){
        Endereco origem = new Endereco();

        origem.setRua("Rua 1");
        origem.setBairro("Bairro 1");
        origem.setCep("87710010");
        origem.setCidade(cidadeService.findById(13L));
        origem.setNumero("1");

//        enderecoRepository.save(origem);
        log.info("Origem {}", origem);
        Endereco destino = new Endereco();

        destino.setRua("Rua 2");
        destino.setBairro("Bairro 2");
        destino.setCep("87710010");
        destino.setCidade(cidadeService.findById(11L));
        destino.setNumero("2");

        log.info("Destino {}", destino);
//        enderecoRepository.save(destino);

        Pedido pedido = new Pedido();

        pedido.setOrigem(enderecoRepository.save(origem));
        pedido.setDestino(enderecoRepository.save(destino));

        log.info("Origem pedido {}", pedido.getOrigem());
        log.info("Destino pedido {}", pedido.getDestino());
//        pedido.setOrigem(enderecoRepository.findById(1L).orElseThrow(
//                () -> new NotFoundException("Origem não encontrada")
//        ));

//        pedido.setDestino(enderecoRepository.findById(2L).orElseThrow(
//                () -> new NotFoundException("Destino não encontrada")
//        ));

        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setPessoa(pessoaFisicaService.findById(4L));

//        pedidoRepository.save(pedido);

        log.info("Pedido {}", pedido);

        Pacote pacote = new Pacote();

        pacote.setAltura(12.2);
        pacote.setPeso(1.2222);
        pacote.setComprimento(122.123456);
        pacote.setLargura(1222.2);
//        pacote.setPedido(pedido);
        pacote.setPedido(pedidoRepository.save(pedido));
        log.info("Pedido pacote {}", pacote.getPedido());

        pacote.setTipo(TipoPacote.CAIXA);
        pacote.setQuantidade(2);
        log.info("Pacote {}", pacote);
        return pacoteRepository.save(pacote);
    }

    public Pacote insert(Pacote pacote) {
        return pacoteRepository.save(pacote);
    }

    public List<Pacote> insertAll(List<Pacote> pacotes) {
        return pacoteRepository.saveAll(pacotes);
    }

    public List<Pacote> findAllByPedidoId(Long id){
        return pacoteRepository.findByPedidoId(id);
    }

}
