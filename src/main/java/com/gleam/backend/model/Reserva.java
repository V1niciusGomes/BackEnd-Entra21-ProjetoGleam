package com.gleam.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataReserva;

    @Column(nullable = false)
    private LocalDateTime dataExpiracao;

    @Column(nullable = false, columnDefinition = "TINYINT UNSIGNED DEFAULT 1")
    private Integer status; // 1: Ativa, 2: Expirada, 3: Convertida em Venda

    private String observacao;
}