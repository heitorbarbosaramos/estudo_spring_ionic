package com.heitor.venda.service;

import com.heitor.venda.builder.PedidoBuilder;
import com.heitor.venda.domain.Pedido;
import com.heitor.venda.repository.PedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PedidoServiceTest {

    @InjectMocks
    private PedidoService service;
    @Mock
    private PedidoRepository repository;

    @Test
    void findById() {

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(PedidoBuilder.criarOptional());

        Pedido pedido = service.findById(1l);

        Assertions.assertNotNull(pedido);
        Assertions.assertEquals(Pedido.class, pedido.getClass());
    }
}