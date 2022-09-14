package com.heitor.venda.exceptions.validation;

import com.heitor.venda.exceptions.resources.MensagemErrorPadrao;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FieldsMessage extends MensagemErrorPadrao {

    private List<FieldMessage> erros = new ArrayList<>();
}
