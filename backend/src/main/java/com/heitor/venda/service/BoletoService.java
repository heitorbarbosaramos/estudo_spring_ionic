package com.heitor.venda.service;

import com.heitor.venda.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoletoService {

    public static PagamentoComBoleto gerarBoleto(){

        PagamentoComBoleto boleto = new PagamentoComBoleto();
        boleto.setDataVencimento(LocalDateTime.now().plusDays(8));
        return  boleto;
    }
}
