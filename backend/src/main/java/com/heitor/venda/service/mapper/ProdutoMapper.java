package com.heitor.venda.service.mapper;

import com.heitor.venda.domain.Produto;
import com.heitor.venda.domain.dto.ProdutoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    Produto toEntity(ProdutoDTO dto);

    ProdutoDTO toDTO(Produto produto);
}
