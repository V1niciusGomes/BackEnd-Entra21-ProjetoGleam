package com.gleam.backend.service;

import com.gleam.backend.dto.FornecedorDTO;
import com.gleam.backend.model.Fornecedor;
import com.gleam.backend.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Fornecedor save(FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(fornecedorDTO.getNome());
        fornecedor.setCnpj(fornecedorDTO.getCnpj());
        fornecedor.setTelefone(fornecedorDTO.getTelefone());
        fornecedor.setDescricao(fornecedorDTO.getDescricao());
        return fornecedorRepository.save(fornecedor);
    }
}