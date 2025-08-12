package com.gleam.backend.service;

import com.gleam.backend.dto.MovimentacaoEstoqueDTO;
import com.gleam.backend.model.MovimentacaoEstoque;
import com.gleam.backend.model.Produto;
import com.gleam.backend.repository.MovimentacaoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovimentacaoEstoqueService {
    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    public MovimentacaoEstoque save(MovimentacaoEstoqueDTO movimentacaoDTO) {
        // Validação dos dados de entrada
        if (movimentacaoDTO.getIdProduto() == null) {
            throw new IllegalArgumentException("O ID do produto é obrigatório.");
        }
        if (movimentacaoDTO.getTipo() == null || movimentacaoDTO.getTipo().isEmpty()) {
            throw new IllegalArgumentException("O tipo da movimentação é obrigatório.");
        }

        // Criação da entidade MovimentacaoEstoque
        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();

        // Configuração do relacionamento com Produto
        Produto produto = new Produto();
        produto.setId(movimentacaoDTO.getIdProduto()); // Define apenas o ID para a relação
        movimentacao.setProduto(produto); // O Hibernate resolverá a relação com base no ID

        // Conversão do tipo para enumeração com validação
        MovimentacaoEstoque.TipoMovimentacao tipo;
        try {
            tipo = MovimentacaoEstoque.TipoMovimentacao.valueOf(movimentacaoDTO.getTipo().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de movimentação inválido. Use 'ENTRADA' ou 'SAIDA'.");
        }
        movimentacao.setTipo(tipo);

        movimentacao.setQuantidade(movimentacaoDTO.getQuantidade());
        movimentacao.setDataMovimentacao(movimentacaoDTO.getDataMovimentacao() != null ? movimentacaoDTO.getDataMovimentacao() : LocalDateTime.now());
        movimentacao.setObservacao(movimentacaoDTO.getObservacao());

        // Persistência no banco
        return movimentacaoEstoqueRepository.save(movimentacao);
    }
}