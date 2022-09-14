package com.heitor.venda.builder;

import com.heitor.venda.domain.Pagamento;
import com.heitor.venda.domain.PagamentoComBoleto;
import com.heitor.venda.enums.EstadoPagamento;

import java.time.LocalDateTime;

public class PagamentoBuilder {

    public static Pagamento criarPagamentoBoleto(){

        PagamentoComBoleto pagamento = new PagamentoComBoleto();
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setEstado(EstadoPagamento.QUITADO);
        pagamento.setDataVencimento(LocalDateTime.now().plusDays(5));
        pagamento.setId(1l);
        return pagamento;
    }
}
