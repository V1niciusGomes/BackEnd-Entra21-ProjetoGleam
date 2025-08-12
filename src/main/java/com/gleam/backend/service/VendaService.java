package com.gleam.backend.service;

import com.gleam.backend.dto.VendaDTO;
import com.gleam.backend.model.Cliente;
import com.gleam.backend.model.Venda;
import com.gleam.backend.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    public Venda save(VendaDTO vendaDTO) {
        Venda venda = new Venda();
        venda.setDataVenda(vendaDTO.getDataVenda() != null ? vendaDTO.getDataVenda() : LocalDateTime.now());
        Cliente cliente = new Cliente();
        cliente.setId(vendaDTO.getIdCliente());
        venda.setCliente(cliente);
        venda.setValorTotal(vendaDTO.getValorTotal());
        venda.setStatus(vendaDTO.getStatus());
        return vendaRepository.save(venda);
    }
}