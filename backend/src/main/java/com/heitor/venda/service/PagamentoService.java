package com.heitor.venda.service;

import com.heitor.venda.domain.Pagamento;
import com.heitor.venda.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    private final PagamentoRepository repo;

    @Autowired
    public PagamentoService(PagamentoRepository repo) {
        this.repo = repo;
    }

    public Pagamento save (Pagamento pagamento){
        return repo.save(pagamento);
    }
}
