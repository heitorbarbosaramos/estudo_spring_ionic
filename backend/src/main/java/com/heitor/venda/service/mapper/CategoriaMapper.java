package com.heitor.venda.service.mapper;

import com.heitor.venda.domain.Categoria;
import com.heitor.venda.domain.dto.CategoriaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    Categoria toEntity(CategoriaDTO categoriaDTO);

    CategoriaDTO toDto(Categoria categoria);
}
