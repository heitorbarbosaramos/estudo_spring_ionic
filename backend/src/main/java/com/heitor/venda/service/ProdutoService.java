package com.heitor.venda.service;

import com.heitor.venda.domain.Categoria;
import com.heitor.venda.domain.Produto;
import com.heitor.venda.domain.dto.ProdutoDTO;
import com.heitor.venda.exceptions.ObjectNotFoundExceptions;
import com.heitor.venda.repository.ProdutoRepository;
import com.heitor.venda.service.mapper.ProdutoMapper;
import com.heitor.venda.util.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repo;
    private final CategoriaService catServ;
    private final ProdutoMapper mapper;

    @Autowired
    public ProdutoService(ProdutoRepository repo, CategoriaService catServ, ProdutoMapper mapper) {
        this.repo = repo;
        this.catServ = catServ;
        this.mapper = mapper;
    }

    public Produto findById(Integer idProduto){
        return repo.findById(idProduto).orElseThrow(()-> new ObjectNotFoundExceptions("Produto não localizado, id: "+idProduto));
    }

    public Page<ProdutoDTO> search(String nome, String idsCategorias, Integer page, Integer size, String direction, String orderBy){
        List<Integer> catIds = URL.decodeIntList(idsCategorias);

        nome = URL.buscaEspacoVazioUrl(nome);

        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = catServ.findAllById(catIds);

        Page<Produto> produtos = repo.search(nome, categorias, pageRequest);
        Page<ProdutoDTO> dtos = produtos.map(mapper::toDTO);

        return dtos;
    }

    public Page<ProdutoDTO> searchFilter(ProdutoDTO dto, Integer page, Integer size, String direction, String orderBy){

        Produto produto = mapper.toEntity(dto);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("nome", mat -> mat.ignoreCase().startsWith())
                .withMatcher("preco", mat-> mat.exact());

        Example<Produto> example = Example.of(produto, matcher);

        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        Pageable pageable = pageRequest;

        Page<Produto> produtos = repo.findAll(example, pageable);
        Page<ProdutoDTO> dtos = produtos.map(mapper::toDTO);

        return dtos;
    }
}
