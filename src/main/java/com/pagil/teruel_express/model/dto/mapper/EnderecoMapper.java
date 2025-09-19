package com.pagil.teruel_express.model.dto.mapper;

import com.pagil.teruel_express.model.dto.EnderecoDTO;
import com.pagil.teruel_express.model.entity.Cidade;
import com.pagil.teruel_express.model.entity.Endereco;
import org.modelmapper.ModelMapper;

public class EnderecoMapper {

    public static Endereco toEndereco(EnderecoDTO dto, Cidade cidade) {
        Endereco endereco = new ModelMapper().map(dto, Endereco.class);
        endereco.setCidade(cidade);
        return endereco;
    }

    public static EnderecoDTO toDto(Endereco endereco) {
        EnderecoDTO dto = new ModelMapper().map(endereco, EnderecoDTO.class);
        dto.setCidade(endereco.getCidade().getNome());
        return dto;
    }

}
