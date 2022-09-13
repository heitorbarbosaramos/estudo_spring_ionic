package com.heitor.venda.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.heitor.venda.enums.EstadoPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Pagamento {

    @Id
    private Long id;
    private EstadoPagamento estado;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
