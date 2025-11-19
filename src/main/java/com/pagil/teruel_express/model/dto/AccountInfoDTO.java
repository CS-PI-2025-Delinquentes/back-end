package com.pagil.teruel_express.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AccountInfoDTO {

    private String name;
    private String accountType;
    private String cpfCnpj;
    private String email;
    private String phone;

}
