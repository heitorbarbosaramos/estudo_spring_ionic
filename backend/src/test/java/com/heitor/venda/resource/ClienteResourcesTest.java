package com.heitor.venda.resource;

import com.heitor.venda.builder.ClienteBuilder;
import com.heitor.venda.domain.Cliente;
import com.heitor.venda.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteResourcesTest {

    @InjectMocks
    private ClienteResources resources;
    @Mock
    private ClienteService service;

    @Test
    void findById() {

        Mockito.doReturn(ClienteBuilder.criarObjeto()).when(service).findById(Mockito.anyInt());

        ResponseEntity<Cliente> response = resources.findById(1);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

    }
}