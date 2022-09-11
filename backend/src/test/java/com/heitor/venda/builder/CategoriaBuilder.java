package com.heitor.venda.builder;

import com.heitor.venda.domain.Categoria;
import com.heitor.venda.domain.dto.CategoriaDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriaBuilder {

    public static Categoria criarObjeto(){
        Categoria categoria = new Categoria();
        categoria.setId(1);
        categoria.setNome("Categoria Teste");
        categoria.getProdutos().add(ProdutoBuilder.criarObjeto());
        return categoria;
    }

    public static CategoriaDTO criarDto(){
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(criarObjeto().getId());
        dto.setNome(criarObjeto().getNome());
        return dto;
    }

    public static List<Categoria> criarLista(){
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(criarObjeto());
        return categorias;
    }

    public static List<CategoriaDTO> criarListaDto(){
        List<CategoriaDTO> list = new ArrayList<>();
        list.add(criarDto());
        return list;
    }

    public static Optional<Categoria> criarOptional(){
       return Optional.of(criarObjeto());
    }

}
