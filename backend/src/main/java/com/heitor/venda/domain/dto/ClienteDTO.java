package com.heitor.venda.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.heitor.venda.enums.TipoCliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    private Integer id;
    private String nome;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String cpfOuCnpj;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TipoCliente tipo;
}
