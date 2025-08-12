package com.gleam.backend.service;

import com.gleam.backend.dto.ItemVendaDTO;
import com.gleam.backend.model.ItemVenda;
import com.gleam.backend.model.ItemVendaId;
import com.gleam.backend.model.Produto;
import com.gleam.backend.model.Venda;
import com.gleam.backend.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemVendaService {
    @Autowired
    private ItemVendaRepository itemVendaRepository;

    public ItemVenda save(ItemVendaDTO itemVendaDTO) {
        ItemVenda itemVenda = new ItemVenda();
        ItemVendaId id = new ItemVendaId(itemVendaDTO.getIdVenda(), itemVendaDTO.getIdProduto());
        itemVenda.setId(id);

        Venda venda = new Venda();
        venda.setId(itemVendaDTO.getIdVenda());
        itemVenda.setVenda(venda);

        Produto produto = new Produto();
        produto.setId(itemVendaDTO.getIdProduto());
        itemVenda.setProduto(produto);

        itemVenda.setQuantidade(itemVendaDTO.getQuantidade());
        itemVenda.setPrecoUnitario(itemVendaDTO.getPrecoUnitario());
        return itemVendaRepository.save(itemVenda);
    }
}