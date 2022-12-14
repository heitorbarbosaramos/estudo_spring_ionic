package com.heitor.venda.resource;

import com.heitor.venda.domain.Cliente;
import com.heitor.venda.domain.dto.ClienteDTO;
import com.heitor.venda.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    public ResponseEntity<ClienteDTO> save(@RequestBody @Valid ClienteDTO clienteDto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(service.novo(clienteDto).getId()).toUri();
        return ResponseEntity.created(uri).build();
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

    @GetMapping("/email")
    public ResponseEntity<Cliente> findByEmail(@RequestParam(name = "email")String email){
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<Page<Cliente>> pageCliente(
            @RequestParam(name = "page",        defaultValue = "0")     Integer page,
            @RequestParam(name = "size",        defaultValue = "24")    Integer size,
            @RequestParam(name = "direction",   defaultValue = "ASC")   String direction,
            @RequestParam(name = "orderBy",     defaultValue = "nome")  String orderBy){
        return ResponseEntity.ok(service.pageCliente(page, size, direction, orderBy));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/pageDto")
    public ResponseEntity<Page<ClienteDTO>> pageClienteDto(
            @RequestParam(name = "page",        defaultValue = "0")     Integer page,
            @RequestParam(name = "size",        defaultValue = "24")    Integer size,
            @RequestParam(name = "direction",   defaultValue = "ASC")   String direction,
            @RequestParam(name = "orderBy",     defaultValue = "NOME")  String orderBy){
        return ResponseEntity.ok(service.pageClienteDto(page, size, direction, orderBy));
    }
}
