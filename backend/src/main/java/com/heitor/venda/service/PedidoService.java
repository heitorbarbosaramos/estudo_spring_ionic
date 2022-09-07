package com.heitor.venda.service;

import com.heitor.venda.domain.Pedido;
import com.heitor.venda.exceptions.ObjectNotFoundExceptions;
import com.heitor.venda.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    @Autowired
    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public Pedido findById(Long pedidoId){
        return repository.findById(pedidoId).orElseThrow(()-> new ObjectNotFoundExceptions("Pedido não localizado de id: " + pedidoId));
    }
}
