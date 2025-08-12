package com.gleam.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimentacaoEstoqueDTO {
    private Long id;
    private Long idProduto;
    private String tipo; // "ENTRADA" ou "SAIDA"
    private Integer quantidade;
    private LocalDateTime dataMovimentacao;
    private String observacao;
}