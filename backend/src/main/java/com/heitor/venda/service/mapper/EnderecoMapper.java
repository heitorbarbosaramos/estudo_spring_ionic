package com.heitor.venda.service.mapper;


import com.heitor.venda.domain.Endereco;
import com.heitor.venda.domain.dto.EnderecoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEntity(EnderecoDTO dto);

    EnderecoDTO toDto(Endereco endereco);
}
