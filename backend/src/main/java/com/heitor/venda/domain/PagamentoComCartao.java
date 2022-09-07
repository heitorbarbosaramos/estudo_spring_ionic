package com.heitor.venda.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class PagamentoComCartao extends Pagamento {

    private Integer numeroDeParcela;
}
