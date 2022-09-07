package com.heitor.venda.builder;

import com.heitor.venda.domain.ItemPedido;

import java.util.HashSet;
import java.util.Set;

public class ItemPedidoBuilder {

    public static ItemPedido criarObjeto(){
        ItemPedido it = new ItemPedido();
        it.setDesconto(0.0);
        it.setId(ProdutoBuilder.criarObjeto(), PedidoBuilder.criarObjeto());
        it.setQuantidade(1);
        return it;
    }

    public static Set<ItemPedido> criarLista(){
        Set<ItemPedido> lista = new HashSet<>();
        lista.add(criarObjeto());
        return lista;
    }
}
