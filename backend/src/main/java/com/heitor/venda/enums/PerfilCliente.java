package com.heitor.venda.enums;

import java.util.ArrayList;
import java.util.List;

public enum PerfilCliente {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private Integer cod;
    private String descricao;
    private PerfilCliente(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static List<PerfilCliente> listEnum(){
        List<PerfilCliente> lista = new ArrayList<>();
        for(PerfilCliente x : PerfilCliente.values()){
            lista.add(x);
        }
        return lista;
    }

    public static PerfilCliente toEnum(Integer codEnum){
        if(codEnum == null){return null;}

        for(PerfilCliente x : PerfilCliente.values()){
            if(x.getCod() == codEnum){
                return x;
            }
        }

        throw new IllegalArgumentException("Cod. Tipo cliente invalido: " + codEnum);
    }
}
