package com.heitor.venda.builder;

import com.heitor.venda.domain.Cliente;
import com.heitor.venda.domain.dto.ClienteDTO;
import com.heitor.venda.enums.TipoCliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.*;

public class ClienteBuilder {

    public static Cliente criarObjeto(){
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setEmail("email@email.com");
        cliente.setNome("Nome do Cliente");
        cliente.setCpfOuCnpj("833.477.960-71");
        cliente.setTipo(TipoCliente.PESSOAFISICA);
        cliente.setEnderecoList(EnderecoBuilder.lista());
        cliente.setTelefones(telefones());
        return cliente;
    }

    public static ClienteDTO criarDto(){
        ClienteDTO dto = new ClienteDTO();
        dto.setId(criarObjeto().getId());
        dto.setNome(criarObjeto().getNome());
        dto.setEmail(criarObjeto().getEmail());
        dto.setTipo(criarObjeto().getTipo());
        dto.setCpfOuCnpj(criarObjeto().getCpfOuCnpj());

        return dto;
    }

    public static Set<String> telefones(){
        Set<String> set = new HashSet<>();
        set.add("(11) 98946-2021");
        set.add("(11) 98541-1034");
        return set;
    }

    public static Optional<Cliente> criarOptional(){
        return Optional.of(criarObjeto());
    }

    public static List<Cliente> criarLista(){
        List<Cliente> list = new ArrayList<>();
        list.add(criarObjeto());
        return list;
    }

    public static Page<Cliente> criarPage(){
        return new PageImpl<>(List.of(criarObjeto()));
    }
    public static List<ClienteDTO> criarListaDtos(){
        List<ClienteDTO> dtos = new ArrayList<>();
        dtos.add(criarDto());
        return dtos;
    }
}
