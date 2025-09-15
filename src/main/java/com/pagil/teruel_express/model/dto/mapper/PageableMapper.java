package com.pagil.teruel_express.model.dto.mapper;

import com.pagil.teruel_express.model.dto.PageableDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class PageableMapper {

    public static PageableDTO toDto(Page page){
            return new ModelMapper().map(page, PageableDTO.class);
        }
}
