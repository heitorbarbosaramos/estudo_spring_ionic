package com.heitor.venda.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class PagamentoComBoleto extends Pagamento{

    private LocalDateTime dataVencimento;
    private LocalDateTime dataPagamento;
}
