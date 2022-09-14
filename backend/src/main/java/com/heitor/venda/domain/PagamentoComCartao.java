package com.heitor.venda.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {

    private Integer numeroDeParcela;
}
