package com.heitor.venda.service;

import com.heitor.venda.builder.CategoriaBuilder;
import com.heitor.venda.domain.Categoria;
import com.heitor.venda.repository.CategoriaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService service;
    @Mock
    private CategoriaRepository repo;

    @Test
    void testFindById(){
        Mockito.doReturn(CategoriaBuilder.criarOptional()).when(repo).findById(Mockito.anyInt());

        Categoria categoria = service.findById(1);

        Assertions.assertEquals(Categoria.class, categoria.getClass());
    }

}