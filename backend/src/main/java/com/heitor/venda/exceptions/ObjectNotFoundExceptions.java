package com.heitor.venda.exceptions;

public class ObjectNotFoundExceptions extends RuntimeException{

    public ObjectNotFoundExceptions(String mensagem){
        super(mensagem);
    }

    public ObjectNotFoundExceptions(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
