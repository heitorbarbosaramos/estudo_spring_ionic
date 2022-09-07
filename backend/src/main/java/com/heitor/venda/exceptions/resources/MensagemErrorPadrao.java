package com.heitor.venda.exceptions.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MensagemErrorPadrao {

    private String statusHttp;
    private String mensagem;
    private String path;
    private LocalDateTime data;
}
