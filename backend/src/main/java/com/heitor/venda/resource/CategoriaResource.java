package com.heitor.venda.resource;

import com.heitor.venda.domain.Categoria;
import com.heitor.venda.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/categoria")
public class CategoriaResource {

    private final CategoriaService service;

    @Autowired
    public CategoriaResource(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> save(Categoria categoria){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.save(categoria).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }
}
