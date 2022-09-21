package com.heitor.venda.resource;

import com.heitor.venda.domain.Produto;
import com.heitor.venda.domain.dto.ProdutoDTO;
import com.heitor.venda.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
public class ProdutoResource {

    private final ProdutoService service;

    @Autowired
    public ProdutoResource(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<Produto> findById(@PathVariable(name = "idProduto") Integer idProduto){
        return ResponseEntity.ok(service.findById(idProduto));
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> searc(
            @RequestParam(name = "nome", defaultValue = "") String nome,
            @RequestParam(name = "idsCategorias", defaultValue = "")String idsCategorias,
            @RequestParam(name = "page", defaultValue = "0")Integer page,
            @RequestParam(name = "size", defaultValue = "24")Integer size,
            @RequestParam(name = "direction", defaultValue = "ASC")String direction,
            @RequestParam(name = "orderBy", defaultValue = "nome")String orderBy
    ){
        return ResponseEntity.ok(service.search(nome, idsCategorias, page, size, direction, orderBy));

    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ProdutoDTO>> searcFilter(
            @RequestParam(name = "nome"         , defaultValue = "") String nome,
            @RequestParam(name = "idsCategorias", defaultValue = "")String idsCategorias,
            @RequestParam(name = "page"         , defaultValue = "0")Integer page,
            @RequestParam(name = "size"         , defaultValue = "24")Integer size,
            @RequestParam(name = "direction"    , defaultValue = "ASC")String direction,
            @RequestParam(name = "orderBy"      , defaultValue = "nome")String orderBy,
            @RequestBody ProdutoDTO dto ){
        return ResponseEntity.ok(service.searchFilter(dto, page, size, direction, orderBy));
    }
}
