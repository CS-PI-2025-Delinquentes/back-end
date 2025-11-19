package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.Avaliacao;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.entity.PessoaFisica;
import com.pagil.teruel_express.model.entity.PessoaJuridica;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AvaliacaoResponseDTO {

    private Long id;
    private Integer nota;
    private String descricao;
    private String nomeAvaliador;
    private LocalDate dataAvaliacao;

    public AvaliacaoResponseDTO(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.nota = avaliacao.getNota();
        this.descricao = avaliacao.getDescricao();
        this.nomeAvaliador = getNomeAvaliador(avaliacao.getPessoa());
        this.dataAvaliacao = avaliacao.getDataAvaliacao();
    }

    public AvaliacaoResponseDTO() {}

    public String getNomeAvaliador(Pessoa pessoa) {
        if (pessoa instanceof PessoaFisica) {
            return ((PessoaFisica) pessoa).getNome();
        } else if (pessoa instanceof PessoaJuridica) {
            return ((PessoaJuridica) pessoa).getNomeFantasia();
        }
        return "N/A";
    }

}
