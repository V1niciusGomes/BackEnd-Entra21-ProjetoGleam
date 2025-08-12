package com.gleam.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataVenda;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(nullable = false)
    private Double valorTotal;

    @Column(nullable = false, columnDefinition = "TINYINT UNSIGNED DEFAULT 2")
    private Integer status; // 1: Concluída, 2: Pendente, 3: Cancelada
}