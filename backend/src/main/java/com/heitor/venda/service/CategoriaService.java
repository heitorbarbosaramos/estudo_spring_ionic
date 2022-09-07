package com.heitor.venda.service;

import com.heitor.venda.domain.Categoria;
import com.heitor.venda.exceptions.ObjectNotFoundExceptions;
import com.heitor.venda.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaRepository repo;

    @Autowired
    public CategoriaService(CategoriaRepository repo) {
        this.repo = repo;
    }

    public Categoria findById(Integer id){
        return repo.findById(id).orElseThrow(()-> new ObjectNotFoundExceptions("Categoria não encontrada, id: " + id));
    }
}
