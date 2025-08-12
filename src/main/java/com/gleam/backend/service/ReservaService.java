package com.gleam.backend.service;

import com.gleam.backend.dto.ReservaDTO;
import com.gleam.backend.model.Cliente;
import com.gleam.backend.model.Reserva;
import com.gleam.backend.model.Produto;
import com.gleam.backend.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva save(ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva();
        Produto produto = new Produto();
        produto.setId(reservaDTO.getIdProduto());
        reserva.setProduto(produto);

        Cliente cliente = new Cliente();
        cliente.setId(reservaDTO.getIdCliente());
        reserva.setCliente(cliente);

        reserva.setQuantidade(reservaDTO.getQuantidade());
        reserva.setDataReserva(reservaDTO.getDataReserva() != null ? reservaDTO.getDataReserva() : LocalDateTime.now());
        reserva.setDataExpiracao(reservaDTO.getDataExpiracao() != null ? reservaDTO.getDataExpiracao() : LocalDateTime.now().plusMonths(1));
        reserva.setStatus(reservaDTO.getStatus());
        reserva.setObservacao(reservaDTO.getObservacao());
        return reservaRepository.save(reserva);
    }
}