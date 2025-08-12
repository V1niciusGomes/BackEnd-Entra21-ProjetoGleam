package com.gleam.backend.controller;

import com.gleam.backend.dto.ReservaDTO;
import com.gleam.backend.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ReservaDTO save(@RequestBody ReservaDTO reservaDTO) {
        reservaService.save(reservaDTO);
        return reservaDTO;
    }
}