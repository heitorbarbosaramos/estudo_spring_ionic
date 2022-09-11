package com.heitor.venda.resource;

import com.heitor.venda.domain.Categoria;
import com.heitor.venda.domain.dto.CategoriaDTO;
import com.heitor.venda.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaResource {

    private final CategoriaService service;

    @Autowired
    public CategoriaResource(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid CategoriaDTO categoria){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.save(categoria).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAllDto(){
        return ResponseEntity.ok(service.findListDto());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoriaDTO>> findAllPageDto(
            @RequestParam(value = "page",       defaultValue = "0") Integer page,
            @RequestParam(value = "size",       defaultValue = "24") Integer size,
            @RequestParam(value = "direction",  defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy",    defaultValue = "NOME") String orderBy){
        return ResponseEntity.ok(service.pageCategoriaDto(page, size, direction, orderBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") Integer id, @RequestBody @Valid CategoriaDTO categoria){
        categoria.setId(id);
        service.update(categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
