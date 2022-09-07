package com.heitor.venda.resource;

import com.heitor.venda.domain.Cliente;
import com.heitor.venda.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteResources {

    private final ClienteService service;

    @Autowired
    public ClienteResources(ClienteService service) {
        this.service = service;
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> findById(@PathVariable(name = "idCliente") Integer idCliente){
        return ResponseEntity.ok(service.findById(idCliente));
    }
}
