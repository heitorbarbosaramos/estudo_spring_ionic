package com.heitor.venda.exceptions.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FieldMessage {

    private String campoNome;
    private String mensagemError;
}
