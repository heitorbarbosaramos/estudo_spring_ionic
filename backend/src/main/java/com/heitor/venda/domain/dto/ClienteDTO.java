package com.heitor.venda.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.heitor.venda.enums.TipoCliente;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ClienteDTO {

    private Integer id;
    @NotEmpty(message = "Campo obrigatorio")
    @Length(min = 5, max = 80, message = "Campo deve conter de 5 a 80 caracteres")
    private String nome;
    @NotEmpty(message = "Campo obrigatorio")
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String cpfOuCnpj;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TipoCliente tipo;
    private Set<String> telefones = new HashSet<>();
    private List<EnderecoDTO> enderecoList = new ArrayList<>();
}
