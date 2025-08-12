package com.gleam.backend.service;

import com.gleam.backend.dto.ProdutoDTO;
import com.gleam.backend.enums.Acabamento;
import com.gleam.backend.enums.TipoMovimentacao;
import com.gleam.backend.model.Categoria;
import com.gleam.backend.model.Fornecedor;
import com.gleam.backend.model.MovimentacaoEstoque;
import com.gleam.backend.model.Produto;
import com.gleam.backend.repository.CategoriaRepository;
import com.gleam.backend.repository.FornecedorRepository;
import com.gleam.backend.repository.MovimentacaoEstoqueRepository;
import com.gleam.backend.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Produto save(ProdutoDTO produtoDTO) {
        // Busca as entidades relacionadas para garantir que existem
        Categoria categoria = categoriaRepository.findById(produtoDTO.getIdCategoria())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " + produtoDTO.getIdCategoria()));

        Fornecedor fornecedor = fornecedorRepository.findById(produtoDTO.getIdFornecedor())
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado com o ID: " + produtoDTO.getIdFornecedor()));

        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPrecoVenda(produtoDTO.getPrecoVenda());
        produto.setPrecoCusto(produtoDTO.getPrecoCusto());

        produto.setCodigoFornecedor(produtoDTO.getCodigoFornecedor());
        produto.setImagem(produtoDTO.getImagem());

        // << LÓGICA ALTERADA PARA LIDAR COM O NÚMERO >>
        Integer acabamentoIndex = produtoDTO.getAcabamento();
        if (acabamentoIndex != null && acabamentoIndex >= 0 && acabamentoIndex < Acabamento.values().length) {
            // Converte o índice (0, 1, 2...) para o enum correspondente
            produto.setAcabamento(Acabamento.values()[acabamentoIndex]);
        } else {
            // Lança um erro se o número for inválido
            throw new IllegalArgumentException("Índice de acabamento inválido: " + acabamentoIndex);
        }

        produto.setCategoria(categoria);
        produto.setFornecedor(fornecedor);

        return produtoRepository.save(produto);
    }

    public Long getQuantidadeTotal(Long idProduto) {
        List<MovimentacaoEstoque> entradas = movimentacaoEstoqueRepository.findByProdutoIdAndTipo(idProduto, TipoMovimentacao.ENTRADA);
        List<MovimentacaoEstoque> saidas = movimentacaoEstoqueRepository.findByProdutoIdAndTipo(idProduto, TipoMovimentacao.SAIDA);

        long totalEntradas = entradas.stream().mapToLong(MovimentacaoEstoque::getQuantidade).sum();
        long totalSaidas = saidas.stream().mapToLong(MovimentacaoEstoque::getQuantidade).sum();

        return totalEntradas - totalSaidas;
    }
}