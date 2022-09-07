package com.heitor.venda.service;

import com.heitor.venda.domain.Cliente;
import com.heitor.venda.exceptions.ObjectNotFoundExceptions;
import com.heitor.venda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    @Autowired
    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public Cliente findById(Integer clienteId){
        return repo.findById(clienteId).orElseThrow(()-> new ObjectNotFoundExceptions("Cliente não encontrado, id: " + clienteId));
    }
}
