package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.Avaliacao;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.entity.PessoaFisica;
import com.pagil.teruel_express.model.entity.PessoaJuridica;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AvaliacaoGetDTO {

    private Long id;
    private Integer nota;
    private String descricao;
    private String nomeAvaliador;
    private LocalDate dataAvaliacao;

    public AvaliacaoGetDTO(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.nota = avaliacao.getNota();
        this.descricao = avaliacao.getDescricao();
        this.nomeAvaliador = getNomeAvaliador(avaliacao.getPessoa());
        this.dataAvaliacao = avaliacao.getDataAvaliacao();
    }

    public AvaliacaoGetDTO() {}

    public String getNomeAvaliador(Pessoa pessoa) {
        if (pessoa instanceof PessoaFisica) {
            return ((PessoaFisica) pessoa).getNome();
        } else if (pessoa instanceof PessoaJuridica) {
            return ((PessoaJuridica) pessoa).getNomeFantasia();
        }
        return "N/A";
    }

}
