package com.gleam.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservaDTO {
    private Long id;
    private Long idProduto;
    private Long idCliente;
    private Integer quantidade;
    private LocalDateTime dataReserva;
    private LocalDateTime dataExpiracao;
    private Integer status; // 1: Ativa, 2: Expirada, 3: Convertida em Venda
    private String observacao;
}