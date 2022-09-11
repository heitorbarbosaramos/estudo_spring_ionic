package com.heitor.venda.service;

import com.heitor.venda.builder.CategoriaBuilder;
import com.heitor.venda.domain.Categoria;
import com.heitor.venda.domain.Produto;
import com.heitor.venda.exceptions.ObjectNotFoundExceptions;
import com.heitor.venda.repository.CategoriaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        List<Produto> produtos = categoria.getProdutos();

        Assertions.assertEquals(Categoria.class, categoria.getClass());
        Assertions.assertNotNull(produtos);
    }

    @Test
    void testFindByIdNotFound(){

        Mockito.when(repo.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundExceptions(Mockito.anyString()));

       try {
           service.findById(1);
       }catch (Exception e){
           Assertions.assertEquals(ObjectNotFoundExceptions.class, e.getClass());
       }
    }

    @Test
    void testSave(){
        Mockito.when(repo.save(Mockito.any())).thenReturn(CategoriaBuilder.criarObjeto());

        Categoria categoria = CategoriaBuilder.criarObjeto();
        categoria.setId(null);

        categoria = service.save(categoria);

        Assertions.assertNotNull(categoria);
        Assertions.assertEquals(Categoria.class, categoria.getClass());

    }

    @Test
    void testUpdate(){
        Mockito.doReturn(CategoriaBuilder.criarOptional()).when(repo).findById(Mockito.anyInt());

        Categoria categoria = CategoriaBuilder.criarObjeto();
        categoria.setNome("Update Categoria");

        Mockito.doReturn(categoria).when(repo).save(Mockito.any());

        categoria = service.update(categoria);

        Assertions.assertEquals(Categoria.class, categoria.getClass());
        Assertions.assertEquals("Update Categoria", categoria.getNome());
    }

}