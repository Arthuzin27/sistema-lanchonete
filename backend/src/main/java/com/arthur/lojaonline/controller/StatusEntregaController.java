package com.arthur.lojaonline.controller;

import com.arthur.lojaonline.dto.request.StatusEntregaRequest;
import com.arthur.lojaonline.dto.response.StatusEntregaResponse;
import com.arthur.lojaonline.service.StatusEntregaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/status-entrega")
@RequiredArgsConstructor
public class StatusEntregaController {

    private final StatusEntregaService statusEntregaService;

    @PostMapping
    public ResponseEntity<StatusEntregaResponse> criar(@Valid @RequestBody StatusEntregaRequest request) {
        StatusEntregaResponse response = statusEntregaService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusEntregaResponse> atualizar(@PathVariable Long id,
            @Valid @RequestBody StatusEntregaRequest request) {
        StatusEntregaResponse response = statusEntregaService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        statusEntregaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusEntregaResponse> buscarPorId(@PathVariable Long id) {
        StatusEntregaResponse response = statusEntregaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<StatusEntregaResponse>> buscarTodos() {
        List<StatusEntregaResponse> responseList = statusEntregaService.buscarTodos();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<StatusEntregaResponse>> buscarPorNome(@RequestParam String nome) {
        List<StatusEntregaResponse> responseList = statusEntregaService.buscarPorNome(nome);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/buscar/exato")
    public ResponseEntity<StatusEntregaResponse> buscarPorNomeExato(@RequestParam String nome) {
        StatusEntregaResponse response = statusEntregaService.buscarPorNomeExato(nome);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/alternar-status")
    public ResponseEntity<StatusEntregaResponse> alternarStatusAtivo(@PathVariable Long id) {
        StatusEntregaResponse response = statusEntregaService.alternarStatusAtivo(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/inicializar")
    public ResponseEntity<String> inicializarStatusPadrao() {
        statusEntregaService.inicializarStatusPadrao();
        return ResponseEntity.status(HttpStatus.CREATED).body("Status padrão de entrega inicializados com sucesso!");
    }
}