package com.heitor.venda.resource;

import com.heitor.venda.domain.Cliente;
import com.heitor.venda.domain.dto.ClienteDTO;
import com.heitor.venda.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteResources {

    private final ClienteService service;

    @Autowired
    public ClienteResources(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody @Valid Cliente cliente){
        return ResponseEntity.ok(service.save(cliente));
    }

    @PutMapping
    public ResponseEntity<ClienteDTO> update(@RequestBody @Valid ClienteDTO clienteDTO){
        return ResponseEntity.ok(service.update(clienteDTO));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> findById(@PathVariable(name = "idCliente") Integer idCliente){
        return ResponseEntity.ok(service.findById(idCliente));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Cliente>> pageCliente(
            @RequestParam(name = "page",        defaultValue = "0")     Integer page,
            @RequestParam(name = "size",        defaultValue = "24")    Integer size,
            @RequestParam(name = "direction",   defaultValue = "ASC")   String direction,
            @RequestParam(name = "orderBy",     defaultValue = "NOME")  String orderBy){
        return ResponseEntity.ok(service.pageCliente(page, size, direction, orderBy));
    }

    @GetMapping("/pageDto")
    public ResponseEntity<Page<ClienteDTO>> pageClienteDto(
            @RequestParam(name = "page",        defaultValue = "0")     Integer page,
            @RequestParam(name = "size",        defaultValue = "24")    Integer size,
            @RequestParam(name = "direction",   defaultValue = "ASC")   String direction,
            @RequestParam(name = "orderBy",     defaultValue = "NOME")  String orderBy){
        return ResponseEntity.ok(service.pageClienteDto(page, size, direction, orderBy));
    }
}
