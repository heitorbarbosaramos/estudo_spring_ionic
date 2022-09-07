package com.heitor.venda.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemPedido {

    @JsonIgnore
    @EmbeddedId
    @Getter
    private ItemPedidoPk id = new ItemPedidoPk();
    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public void setId(Produto produto, Pedido pedido) {
        this.id.setPedido(pedido);
        this.id.setProduto(produto);
        this.preco = this.id.getProduto().getPreco() * quantidade;
    }
}
