package com.heitor.venda.service;

import com.heitor.venda.builder.ClienteBuilder;
import com.heitor.venda.domain.Cliente;
import com.heitor.venda.repository.ClienteRepository;
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

    @Test
    void findById() {

        Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(ClienteBuilder.criarOptional());

        Cliente cliente = service.findById(1);

        Assertions.assertNotNull(cliente);
        Assertions.assertEquals(Cliente.class, cliente.getClass());
    }
}