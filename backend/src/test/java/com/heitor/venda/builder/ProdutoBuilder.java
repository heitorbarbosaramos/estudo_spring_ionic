package com.heitor.venda.builder;

import com.heitor.venda.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoBuilder {

    public static Produto criarObjeto(){
        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Produto A");
        produto.setPreco(10.0);
        return produto;
    }

    public static List<Produto> criarLista(){
        List<Produto> produtos = new ArrayList<>();
        produtos.add(criarObjeto());
        return produtos;
    }
}
