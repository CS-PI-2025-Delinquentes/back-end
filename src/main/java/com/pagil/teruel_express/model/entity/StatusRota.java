package com.pagil.teruel_express.model.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum StatusRota {
    Ativo,
    Inativo,
    Excluido;
}