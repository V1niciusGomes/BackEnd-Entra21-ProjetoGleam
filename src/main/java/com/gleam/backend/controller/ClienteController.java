package com.gleam.backend.controller;

import com.gleam.backend.dto.ClienteDTO;
import com.gleam.backend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ClienteDTO save(@RequestBody ClienteDTO clienteDTO) {
        clienteService.save(clienteDTO);
        return clienteDTO;
    }
}