package com.heitor.venda.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime instate;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_entrega_id")
    private Endereco enderecoEntrega;

    @OneToOne
    @JoinColumn(name = "pagamento_id")
    private Pagamento pagamento;

    @OneToMany(mappedBy="id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    public Double getValorTotal(){
        Double total = 0.0;
        for (ItemPedido x : this.itens){
            total += x.getSubTotal();
        }
        return total;
    }
}
