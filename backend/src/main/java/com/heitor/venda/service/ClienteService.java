package com.heitor.venda.service;

import com.heitor.venda.domain.Cliente;
import com.heitor.venda.domain.dto.ClienteDTO;
import com.heitor.venda.exceptions.ObjectNotFoundExceptions;
import com.heitor.venda.repository.ClienteRepository;
import com.heitor.venda.service.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteRepository repo;
    private final ClienteMapper mapper;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ClienteService(ClienteRepository repo, ClienteMapper mapper, BCryptPasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Cliente save(Cliente cliente){
        return repo.save(cliente);
    }

    public ClienteDTO novo(ClienteDTO clienteDTO){
        clienteDTO.setSenha(clienteDTO.getSenha());
        Cliente cliente = mapper.toEntity(clienteDTO);
        return mapper.toDto(save(cliente));
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

    public List<ClienteDTO> findAll(){
        return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public Page<Cliente> pageCliente(Integer page, Integer size, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Page<ClienteDTO> pageClienteDto(Integer page, Integer size, String direction, String orderBy){
        Page<Cliente> pageCliente = pageCliente(page, size, direction, orderBy);
        Page<ClienteDTO> pageClienteDto = pageCliente.map(mapper::toDto);
        return pageClienteDto;
    }

    private String encodarSenha(String senha){
        return passwordEncoder.encode(senha);
    }
}
