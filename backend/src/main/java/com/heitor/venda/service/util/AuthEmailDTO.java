package com.heitor.venda.service.util;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AuthEmailDTO {

    @NotEmpty(message = "Campo obrigatorio")
    @Email(message = "Email invalido")
    private String email;
}
