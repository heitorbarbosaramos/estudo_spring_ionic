package com.heitor.venda.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento{

    private LocalDateTime dataVencimento;
    private LocalDateTime dataPagamento;
}
