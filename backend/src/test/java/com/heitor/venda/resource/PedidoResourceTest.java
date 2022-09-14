package com.heitor.venda.resource;

import com.heitor.venda.builder.PedidoBuilder;
import com.heitor.venda.domain.Pedido;
import com.heitor.venda.service.PedidoService;
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
class PedidoResourceTest {

    @InjectMocks
    private PedidoResource resource;
    @Mock
    private PedidoService service;

    @Test
    void findById() {

        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(PedidoBuilder.criarObjeto());

        ResponseEntity<Pedido> response = resource.findById(1l);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}