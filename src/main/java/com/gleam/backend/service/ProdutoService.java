package com.gleam.backend.service;

import com.gleam.backend.dto.ProdutoDTO;
import com.gleam.backend.model.Categoria;
import com.gleam.backend.model.Fornecedor;
import com.gleam.backend.model.MovimentacaoEstoque;
import com.gleam.backend.model.Produto;
import com.gleam.backend.repository.MovimentacaoEstoqueRepository;
import com.gleam.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    public Produto save(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPrecoVenda(produtoDTO.getPrecoVenda());
        produto.setPrecoCusto(produtoDTO.getPrecoCusto());
        produto.setMaterial(produtoDTO.getMaterial());
        produto.setAcabamento(produtoDTO.getAcabamento());
        produto.setCodigoBarras(produtoDTO.getCodigoBarras());
        produto.setImagem(produtoDTO.getImagem());

        // Configurar relações
        Categoria categoria = new Categoria();
        categoria.setId(produtoDTO.getIdCategoria());
        produto.setCategoria(categoria);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(produtoDTO.getIdFornecedor());
        produto.setFornecedor(fornecedor);

        return produtoRepository.save(produto);
    }

    public Long getQuantidadeTotal(Long idProduto) {
        List<MovimentacaoEstoque> entradas = movimentacaoEstoqueRepository.findByProdutoIdAndTipo(idProduto, MovimentacaoEstoque.TipoMovimentacao.ENTRADA);
        List<MovimentacaoEstoque> saidas = movimentacaoEstoqueRepository.findByProdutoIdAndTipo(idProduto, MovimentacaoEstoque.TipoMovimentacao.SAIDA);

        Long totalEntradas = entradas.stream().mapToLong(MovimentacaoEstoque::getQuantidade).sum();
        Long totalSaidas = saidas.stream().mapToLong(MovimentacaoEstoque::getQuantidade).sum();

        return totalEntradas - totalSaidas;
    }
}