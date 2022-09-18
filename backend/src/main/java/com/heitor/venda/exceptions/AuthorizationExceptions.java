package com.heitor.venda.exceptions;

public class AuthorizationExceptions extends RuntimeException{

    public AuthorizationExceptions(String mensagem){
        super(mensagem);
    }

    public AuthorizationExceptions(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
