package com.heitor.venda.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CategoriaDTO {

    private Integer id;
    @NotEmpty
    @Length(min = 5, max = 50, message = "Campo nome deve conter de 5 a 50 caracteres")
    private String nome;
}
