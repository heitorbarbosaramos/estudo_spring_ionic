package com.heitor.venda.service.validacoes;

import com.heitor.venda.domain.dto.ClienteDTO;
import com.heitor.venda.enums.TipoCliente;
import com.heitor.venda.exceptions.validation.FieldMessage;
import com.heitor.venda.util.ValidaDocumentos;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteDTO> {

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA) && !ValidaDocumentos.validaCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if(objDto.getTipo().equals(TipoCliente.PESSOAJURUDICA) && !ValidaDocumentos.validaCNPJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMensagemError())
                    .addPropertyNode(e.getCampoNome()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}