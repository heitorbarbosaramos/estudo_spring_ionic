package com.heitor.venda.service;

import com.heitor.venda.builder.ClienteBuilder;
import com.heitor.venda.domain.Cliente;
import com.heitor.venda.domain.dto.ClienteDTO;
import com.heitor.venda.repository.ClienteRepository;
import com.heitor.venda.service.mapper.ClienteMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteServiceTest {

    @InjectMocks
    private ClienteService service;
    @Mock
    private ClienteRepository repo;
    @Mock
    private ClienteMapper mapper;

    @Test
    void save(){
        Mockito.when(repo.save(Mockito.any())).thenReturn(ClienteBuilder.criarObjeto());

        Cliente cliente = service.save(ClienteBuilder.criarObjeto());

        Assertions.assertEquals(Cliente.class, cliente.getClass());
    }

    @Test
    void update(){
        Mockito.when(mapper.toEntity(Mockito.any())).thenReturn(ClienteBuilder.criarObjeto());
        Mockito.when(repo.save(Mockito.any())).thenReturn(ClienteBuilder.criarObjeto());
        Mockito.when(mapper.toDto(Mockito.any())).thenReturn(ClienteBuilder.criarDto());

        ClienteDTO dto = service.update(ClienteBuilder.criarDto());

        Assertions.assertEquals(ClienteDTO.class, dto.getClass());
    }

    @Test
    void findById() {

        Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(ClienteBuilder.criarOptional());

        Cliente cliente = service.findById(1);

        Assertions.assertNotNull(cliente);
        Assertions.assertEquals(Cliente.class, cliente.getClass());
    }

    @Test
    void delete(){
        Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(ClienteBuilder.criarOptional());
        Mockito.doNothing().when(repo).delete(Mockito.any());

        service.delete(ClienteBuilder.criarDto().getId());

        Mockito.verify(repo, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void findAllDto(){
        Mockito.when(mapper.toDto(Mockito.any())).thenReturn(ClienteBuilder.criarDto());
        Mockito.when(repo.findAll()).thenReturn(ClienteBuilder.criarLista());

        List<ClienteDTO> list = service.findAll();

        Assertions.assertEquals(ArrayList.class, list.getClass());
        Assertions.assertEquals(ClienteDTO.class, list.get(0).getClass());
    }

    @Test
    void pageCliente(){
        PageRequest pageRequest = PageRequest.of(0, 1, Sort.Direction.valueOf("ASC"), "NOME");

        Mockito.when(repo.findAll(pageRequest)).thenReturn(ClienteBuilder.criarPage());

        Page<Cliente> pageCliente = service.pageCliente(0, 1, "ASC", "NOME");

        Assertions.assertNotNull(pageCliente);
        Assertions.assertEquals(Cliente.class, pageCliente.getContent().get(0).getClass());
    }

    @Test
    void pageClienteDto(){
        PageRequest pageRequest = PageRequest.of(0, 1, Sort.Direction.valueOf("ASC"), "NOME");

        Mockito.when(repo.findAll(pageRequest)).thenReturn(ClienteBuilder.criarPage());
        Mockito.when(mapper.toDto(Mockito.any())).thenReturn(ClienteBuilder.criarDto());

        Page<ClienteDTO> pageCliente = service.pageClienteDto(0, 1, "ASC", "NOME");

        Assertions.assertNotNull(pageCliente);
        Assertions.assertEquals(ClienteDTO.class, pageCliente.getContent().get(0).getClass());
    }
}