package com.heitor.venda.service;

import com.heitor.venda.domain.Categoria;
import com.heitor.venda.domain.dto.CategoriaDTO;
import com.heitor.venda.exceptions.ObjectNotFoundExceptions;
import com.heitor.venda.repository.CategoriaRepository;
import com.heitor.venda.service.mapper.CategoriaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository repo;

    private final CategoriaMapper mapper;

    @Autowired
    public CategoriaService(CategoriaRepository repo, CategoriaMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Categoria save(Categoria categoria){
        return repo.save(categoria);
    }

    public Categoria findById(Integer id){
        return repo.findById(id).orElseThrow(()-> new ObjectNotFoundExceptions("Categoria não encontrada, id: " + id));
    }

    public List<CategoriaDTO> findListDto(){
        return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public Page<Categoria> pageCategoria(Integer page, Integer size, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Page<CategoriaDTO> pageCategoriaDto(Integer page, Integer size, String direction, String orderBy){
        Page<Categoria> categorias = pageCategoria(page, size, direction, orderBy);
        Page<CategoriaDTO> dtos = categorias.map(mapper::toDto);
        return dtos;
    }

    public Categoria update(Categoria categoria){
        findById(categoria.getId());
        return save(categoria);
    }

    public void delete(Integer id){
        repo.delete(findById(id));
    }
}
