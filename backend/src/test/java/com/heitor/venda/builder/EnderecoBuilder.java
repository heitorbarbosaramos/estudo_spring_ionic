package com.heitor.venda.builder;

import com.heitor.venda.domain.Endereco;

import java.util.ArrayList;
import java.util.List;

public class EnderecoBuilder {

    public static Endereco criarObjeto(){
        Endereco endereco = new Endereco();
        endereco.setCep("06725-050");
        endereco.setLogradouro("Rua Horácio Batista da Silva");
        endereco.setComplemento("");
        endereco.setBairro("Jardim São Luiz (Caucaia do Alto)");
        endereco.setLocalidade("Cotia");
        endereco.setUf("SP");
        endereco.setIbge("3513009");
        endereco.setGia("2781");
        endereco.setDdd("11");
        endereco.setSiafi("6361");
        return endereco;
    }

    public static List<Endereco> lista(){
        List<Endereco> lista = new ArrayList<>();
        lista.add(criarObjeto());
        return lista;
    }
}
