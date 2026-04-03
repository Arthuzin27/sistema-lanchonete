package com.arthur.lojaonline.controller;

import com.arthur.lojaonline.dto.request.EnderecosRequest;
import com.arthur.lojaonline.dto.response.EnderecosResponse;
import com.arthur.lojaonline.service.EnderecoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecosController {

    private final EnderecoService enderecosService;

    public EnderecosController(EnderecoService enderecosService) {
        this.enderecosService = enderecosService;
    }

    @PostMapping
    public ResponseEntity<EnderecosResponse> cadastrarEndereco(@RequestBody @Valid EnderecosRequest request) {
        EnderecosResponse response = enderecosService.cadastrarEnderecos(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecosResponse> buscarPorId(@PathVariable Long id) {
        EnderecosResponse response = enderecosService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EnderecosResponse>> listarTodos() {
        List<EnderecosResponse> enderecos = enderecosService.listarTodos();
        return ResponseEntity.ok(enderecos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecosResponse> atualizarEndereco(@PathVariable Long id, @RequestBody @Valid EnderecosRequest request) {
        EnderecosResponse response = enderecosService.atualizarEnderecos(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
        enderecosService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }
}