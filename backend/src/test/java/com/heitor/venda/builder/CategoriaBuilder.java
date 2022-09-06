package com.heitor.venda.builder;

import com.heitor.venda.domain.Categoria;

import java.util.Optional;

public class CategoriaBuilder {

    public static Categoria criarObjeto(){
        Categoria categoria = new Categoria();
        categoria.setId(1);
        categoria.setNome("Categoria Teste");
        return categoria;
    }

    public static Optional<Categoria> criarOptional(){
       return Optional.of(criarObjeto());
    }
}
