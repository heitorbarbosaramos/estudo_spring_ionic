package com.heitor.venda.service;

import com.heitor.venda.domain.Categoria;
import com.heitor.venda.repository.CategoriaRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CategoriaService {

    private final CategoriaRepository repo;

    @Autowired
    public CategoriaService(CategoriaRepository repo) {
        this.repo = repo;
    }

    public Categoria findById(Integer id){
        return repo.findById(id).orElseThrow();
    }
}
