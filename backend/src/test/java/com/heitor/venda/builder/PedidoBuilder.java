package com.heitor.venda.builder;

import com.heitor.venda.domain.Pedido;

import java.time.LocalDateTime;
import java.util.Optional;

public class PedidoBuilder {

    public static Pedido criarObjeto(){
        Pedido pedido = new Pedido();
        pedido.setId(1l);
        pedido.setInstate(LocalDateTime.now());
        pedido.setPagamento(PagamentoBuilder.criarPagamentoBoleto());
        pedido.setCliente(ClienteBuilder.criarObjeto());
        pedido.setEnderecoEntrega(ClienteBuilder.criarObjeto().getEnderecoList().stream().findFirst().get());
        return pedido;
    }

    public static Optional<Pedido> criarOptional(){
        return Optional.of(criarObjeto());
    }
}
