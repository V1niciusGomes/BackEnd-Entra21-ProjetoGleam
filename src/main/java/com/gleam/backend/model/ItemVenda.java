package com.gleam.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ItemVenda {
    @EmbeddedId
    private ItemVendaId id;

    @ManyToOne
    @MapsId("idVenda")
    @JoinColumn(name = "id_venda")
    private Venda venda;

    @ManyToOne
    @MapsId("idProduto")
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double precoUnitario;
}