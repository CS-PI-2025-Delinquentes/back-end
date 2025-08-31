package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.Avaliacao;
import com.pagil.teruel_express.model.entity.Pessoa;
import com.pagil.teruel_express.model.entity.PessoaFisica;
import com.pagil.teruel_express.model.entity.PessoaJuridica;
import lombok.Data;

@Data
public class AvaliacaoGetDTO {
    private Integer nota;
    private String descricao;
    private String nomeAvaliador;

    public AvaliacaoGetDTO(Avaliacao avaliacao) {
        this.nota = avaliacao.getNota();
        this.descricao = avaliacao.getDescricao();
        this.nomeAvaliador = getNomeAvaliador(avaliacao.getPessoa());
    }

    public AvaliacaoGetDTO() {}

    public String getNomeAvaliador(Pessoa pessoa) {
        if (pessoa instanceof PessoaFisica) {
            return ((PessoaFisica) pessoa).getNome();
        } else if (pessoa instanceof PessoaJuridica) {
            return ((PessoaJuridica) pessoa).getNome_fantasia();
        }
        return "N/A";
    }

}
