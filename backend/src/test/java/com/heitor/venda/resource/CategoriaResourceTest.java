package com.heitor.venda.resource;

import com.heitor.venda.builder.CategoriaBuilder;
import com.heitor.venda.domain.Categoria;
import com.heitor.venda.service.CategoriaService;
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
class CategoriaResourceTest {

    @InjectMocks
    private CategoriaResource resource;
    @Mock
    private CategoriaService service;

    @Test
    void findById() {

        Mockito.doReturn(CategoriaBuilder.criarObjeto()).when(service).findById(Mockito.anyInt());

        ResponseEntity response = resource.findById(1);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void save(){

        Mockito.doReturn(CategoriaBuilder.criarObjeto()).when(service).save(Mockito.any());

        ResponseEntity<Void> response = resource.save(CategoriaBuilder.criarObjeto());

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    void update(){
        Mockito.doReturn(Mockito.mock(Categoria.class)).when(service).update(Mockito.any());

        ResponseEntity<Void> response = resource.update(1, CategoriaBuilder.criarObjeto());

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void delete(){
        Mockito.doNothing().when(service).delete(Mockito.anyInt());

        ResponseEntity<Void> response = resource.delete(1);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}