package com.gleam.backend.controller;

import com.gleam.backend.dto.FornecedorDTO;
import com.gleam.backend.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public FornecedorDTO save(@RequestBody FornecedorDTO fornecedorDTO) {
        fornecedorService.save(fornecedorDTO);
        return fornecedorDTO;
    }
}