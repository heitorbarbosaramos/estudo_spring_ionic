package com.heitor.venda.resource;

import com.heitor.venda.domain.Pedido;
import com.heitor.venda.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoResource {

    private final PedidoService service;

    @Autowired
    public PedidoResource(PedidoService service) {
        this.service = service;
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<Pedido> findById(@PathVariable(name = "idPedido")Long idPedido){
        return ResponseEntity.ok(service.findById(idPedido));
    }
}
