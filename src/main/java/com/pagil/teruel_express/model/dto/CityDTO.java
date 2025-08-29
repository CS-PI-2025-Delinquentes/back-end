package com.pagil.teruel_express.model.dto;

import com.pagil.teruel_express.model.entity.StatusRota;
import lombok.Data;

@Data
public class CityDTO {
    private String name;
    private Long estadoId;
    private StatusRota status;
}