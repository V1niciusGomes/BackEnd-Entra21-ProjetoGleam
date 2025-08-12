package com.gleam.backend.dto;

import lombok.Data;

@Data
public class FornecedorDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String descricao;
}