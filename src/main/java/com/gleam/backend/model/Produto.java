package com.gleam.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Double precoVenda;
    private Double precoCusto;
    private String material;
    private Integer acabamento;  // 0: Ouro, 1: Prata, 2: Aço
    private String codigoBarras;
    private String imagem;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;
}