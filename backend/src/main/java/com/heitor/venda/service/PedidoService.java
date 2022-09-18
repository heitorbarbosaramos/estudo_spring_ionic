package com.heitor.venda.service;

import com.heitor.venda.domain.Cliente;
import com.heitor.venda.domain.ItemPedido;
import com.heitor.venda.domain.PagamentoComBoleto;
import com.heitor.venda.domain.Pedido;
import com.heitor.venda.enums.EstadoPagamento;
import com.heitor.venda.exceptions.AuthorizationExceptions;
import com.heitor.venda.exceptions.ObjectNotFoundExceptions;
import com.heitor.venda.repository.PedidoRepository;
import com.heitor.venda.seguranca.SpringUserSecurity;
import com.heitor.venda.seguranca.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PedidoService {

    private final PedidoRepository repo;
    private final PagamentoService pagServ;
    private final ProdutoService prodServ;
    private final ItemPedidoService itemServ;

    private final ClienteService clienteService;

    @Autowired
    public PedidoService(PedidoRepository repo, PagamentoService pagServ, ProdutoService prodServ, ItemPedidoService itemServ, ClienteService clienteService) {
        this.repo = repo;
        this.pagServ = pagServ;
        this.prodServ = prodServ;
        this.itemServ = itemServ;
        this.clienteService = clienteService;
    }

    public Pedido save(Pedido pedido){

       if(pedido.getId() == null){
           pedido.setId(null);
           pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
           pedido.setInstate(LocalDateTime.now());
       }
       if(pedido.getPagamento() instanceof PagamentoComBoleto){
           pedido.setPagamento(BoletoService.gerarBoleto());
       }
       pedido.getPagamento().setPedido(pedido);
       pedido = repo.save(pedido);
       pagServ.save(pedido.getPagamento());

       for(ItemPedido x : pedido.getItens()){
           x.setDesconto(0.0);
           x.setPreco(prodServ.findById(x.getId().getProduto().getId()).getPreco());
       }

       itemServ.save(pedido.getItens());

       return pedido;
    }

    public Pedido findById(Long pedidoId){
        return repo.findById(pedidoId).orElseThrow(()-> new ObjectNotFoundExceptions("Pedido não localizado de id: " + pedidoId));
    }

    public Page<Pedido> findPedidoCliente(Integer page, Integer size, String direction, String orderBy){

        SpringUserSecurity userSecurity = UserService.authentication();

        if(userSecurity == null){
            throw new AuthorizationExceptions("ACESSO NEGADO");
        }

        Cliente cliente = clienteService.findById(userSecurity.getId());
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);

        Page<Pedido> pedidos = repo.findByCliente(cliente, pageRequest);
        return pedidos;
    }
}
