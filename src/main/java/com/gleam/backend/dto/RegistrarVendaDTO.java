package com.gleam.backend.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RegistrarVendaDTO {
    // --- Campos de Entrada ---
    private String nomeCliente;
    private Integer situacao;
    private Integer formaPagamento;
    private Integer numeroParcelas;

    // --- Campos de Saída ---
    private Long id;
    private String nome;
    private BigDecimal precoTotalVenda;
    private List<ItemVendidoDTO> itens;

    // CORREÇÃO AQUI: Garante que o campo dataCriacao existe
    private LocalDateTime dataCriacao;
}