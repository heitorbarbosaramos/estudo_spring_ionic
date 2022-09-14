package com.heitor.venda.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;

    @ManyToMany
    @JoinTable(name = "PRODUTO_CATEGORIA",
        joinColumns = @JoinColumn(name = "produto_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id"))
    private List<Categoria> categorias = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy="id.produto")
    private Set<ItemPedido> itens = new HashSet<>();
}
