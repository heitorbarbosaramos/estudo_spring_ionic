package com.heitor.venda.enums;

import net.bytebuddy.implementation.bytecode.Throw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURUDICA(2, "Pessoa Jurídica");

    private Integer cod;
    private String descricao;
    private TipoCliente(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static List<TipoCliente> listEnum(){
        List<TipoCliente> lista = new ArrayList<>();
        for(TipoCliente x : TipoCliente.values()){
            lista.add(x);
        }
        return lista;
    }

    public static TipoCliente toEnum(Integer codEnum){
        if(codEnum == null){return null;}

        for(TipoCliente x : TipoCliente.values()){
            if(x.getCod() == codEnum){
                return x;
            }
        }

        throw new IllegalArgumentException("Cod. Tipo cliente invalido: " + codEnum);
    }
}
