package com.heitor.venda.service;

import com.heitor.venda.builder.CategoriaBuilder;
import com.heitor.venda.domain.Categoria;
import com.heitor.venda.domain.Produto;
import com.heitor.venda.domain.dto.CategoriaDTO;
import com.heitor.venda.exceptions.ObjectNotFoundExceptions;
import com.heitor.venda.repository.CategoriaRepository;
import com.heitor.venda.service.mapper.CategoriaMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService service;
    @Mock
    private CategoriaRepository repo;
    @Mock
    private CategoriaMapper mapper;

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

        CategoriaDTO dto = CategoriaBuilder.criarDto();
        dto.setId(null);

        Categoria categoria = service.save(dto);

        Assertions.assertNotNull(categoria);
        Assertions.assertEquals(Categoria.class, categoria.getClass());

    }

    @Test
    void testUpdate(){
        Mockito.doReturn(CategoriaBuilder.criarOptional()).when(repo).findById(Mockito.anyInt());

        CategoriaDTO categoriaDTO = CategoriaBuilder.criarDto();
        categoriaDTO.setNome("Update Categoria");

        Categoria categoria = CategoriaBuilder.criarObjeto();
        categoria.setNome(categoriaDTO.getNome());

        Mockito.when(mapper.toEntity(categoriaDTO)).thenReturn(categoria);
        Mockito.when(mapper.toDto(categoria)).thenReturn(categoriaDTO);

        Mockito.doReturn(categoria).when(repo).save(Mockito.any());


        categoriaDTO = service.update(categoriaDTO);

        Assertions.assertEquals(CategoriaDTO.class, categoriaDTO.getClass());
        Assertions.assertEquals("Update Categoria", categoriaDTO.getNome());
    }

    @Test
    void delete(){
        Mockito.doNothing().when(repo).delete(Mockito.any());
        Mockito.doReturn(CategoriaBuilder.criarOptional()).when(repo).findById(Mockito.anyInt());

        service.delete(CategoriaBuilder.criarObjeto().getId());

        Mockito.verify(repo, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void findListDto(){
        Mockito.doReturn(CategoriaBuilder.criarLista()).when(repo).findAll();
        Mockito.when(mapper.toDto(Mockito.any())).thenReturn(CategoriaBuilder.criarDto());

        List<CategoriaDTO> list = service.findListDto();

        Assertions.assertNotNull(list);
        Assertions.assertEquals(ArrayList.class, list.getClass());
        Assertions.assertEquals(CategoriaDTO.class, list.get(0).getClass());
    }

    @Test
    void pageRequest(){
        PageRequest pageRequest = PageRequest.of(0, 1, Sort.Direction.valueOf("ASC"), "NOME");
        Mockito.doReturn(new PageImpl<>(List.of(CategoriaBuilder.criarObjeto()))).when(repo).findAll(pageRequest);

        Page<Categoria> pageCategoria = service.pageCategoria(0, 1, "ASC", "NOME");

        Assertions.assertNotNull(pageCategoria);
        Assertions.assertEquals(Categoria.class,  pageCategoria.getContent().get(0).getClass());
    }

    @Test
    void pageCategoriaDto(){
        PageRequest pageRequest = PageRequest.of(0, 1, Sort.Direction.valueOf("ASC"), "NOME");
        Mockito.doReturn(new PageImpl<>(List.of(CategoriaBuilder.criarObjeto()))).when(repo).findAll(pageRequest);
        Mockito.when(mapper.toDto(Mockito.any())).thenReturn(CategoriaBuilder.criarDto());

        Page<CategoriaDTO> categoriaDTOS = service.pageCategoriaDto(0, 1, "ASC", "NOME");

        Assertions.assertNotNull(categoriaDTOS);
        Assertions.assertEquals(CategoriaDTO.class,  categoriaDTOS.getContent().get(0).getClass());
    }
}