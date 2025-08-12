package com.gleam.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VendaDTO {
    private Long id;
    private LocalDateTime dataVenda;
    private Long idCliente;
    private Double valorTotal;
    private Integer status; // 1: Concluída, 2: Pendente, 3: Cancelada
}