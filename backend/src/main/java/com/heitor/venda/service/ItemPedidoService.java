package com.heitor.venda.service;

import com.heitor.venda.domain.ItemPedido;
import com.heitor.venda.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository repo;

    @Autowired
    public ItemPedidoService(ItemPedidoRepository repo) {
        this.repo = repo;
    }

    public Set<ItemPedido> save(Set<ItemPedido> itemPedido){
        itemPedido.forEach(l->{
            repo.save(l);
        });
        return itemPedido;
    }
}
