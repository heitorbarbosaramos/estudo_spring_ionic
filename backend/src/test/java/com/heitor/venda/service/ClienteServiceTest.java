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

        Mockito.verify(repo, Mockito.times(1) );
    }
}