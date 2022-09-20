package com.heitor.venda.resource;

import com.heitor.venda.domain.Endereco;
import com.heitor.venda.service.util.BuscaEnderecoPorCep;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util")
public class UtilResouces {

    @GetMapping("/cep/{cep}")
    public ResponseEntity<Endereco> buscaEnderecoPorCep(@PathVariable(name = "cep") String cep){
        return ResponseEntity.ok(BuscaEnderecoPorCep.buscarCep(cep));
    }
}
