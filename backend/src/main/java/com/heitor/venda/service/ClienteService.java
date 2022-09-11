package com.heitor.venda.service;

import com.heitor.venda.domain.Cliente;
import com.heitor.venda.domain.dto.ClienteDTO;
import com.heitor.venda.exceptions.ObjectNotFoundExceptions;
import com.heitor.venda.repository.ClienteRepository;
import com.heitor.venda.service.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository repo;
    private final ClienteMapper mapper;

    @Autowired
    public ClienteService(ClienteRepository repo, ClienteMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Cliente save(Cliente cliente){
        return repo.save(cliente);
    }

    public ClienteDTO update(ClienteDTO clienteDTO){
        Cliente cliente = save(mapper.toEntity(clienteDTO));
        return mapper.toDto(cliente);
    }

    public void delete(Integer clienteId){
        repo.delete(findById(clienteId));
    }

    public Cliente findById(Integer clienteId){
        return repo.findById(clienteId).orElseThrow(()-> new ObjectNotFoundExceptions("Cliente não encontrado, id: " + clienteId));
    }
}
