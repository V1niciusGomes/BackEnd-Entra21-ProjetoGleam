package com.gleam.backend.dto;

import lombok.Data;

@Data
public class ProdutoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double precoVenda;
    private Double precoCusto;
    private String material;
    private Integer acabamento; // 0: Ouro, 1: Prata, 2: Aço
    private String codigoBarras;
    private String imagem;
    private Long idCategoria;
    private Long idFornecedor;
}