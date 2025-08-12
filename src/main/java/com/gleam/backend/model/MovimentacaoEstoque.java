package com.gleam.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class MovimentacaoEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo; // ENUM('ENTRADA', 'SAIDA')

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataMovimentacao;

    private String observacao;

    public enum TipoMovimentacao {
        ENTRADA, SAIDA
    }
}