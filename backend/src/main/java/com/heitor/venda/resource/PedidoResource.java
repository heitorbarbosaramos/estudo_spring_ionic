package com.heitor.venda.resource;

import com.heitor.venda.domain.Pedido;
import com.heitor.venda.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Page<Pedido>> findPedidoCliente(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "24") Integer size,
            @RequestParam(name = "direction", defaultValue = "DESC") String direction,
            @RequestParam(name = "orderBy", defaultValue = "instate") String orderBy
    ){
        return ResponseEntity.ok(service.findPedidoCliente(page, size, direction, orderBy));
    }
}
