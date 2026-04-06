package com.arthur.lojaonline.controller;

import com.arthur.lojaonline.dto.request.StatusPagamentoRequest;
import com.arthur.lojaonline.dto.response.StatusPagamentoResponse;
import com.arthur.lojaonline.service.StatusPagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status-pagamento")
@RequiredArgsConstructor
public class StatusPagamentoController {
    
    private final StatusPagamentoService statusPagamentoService;
    
    @PostMapping
    public ResponseEntity<StatusPagamentoResponse> criar(
            @Valid @RequestBody StatusPagamentoRequest request) {
        StatusPagamentoResponse response = statusPagamentoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<StatusPagamentoResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody StatusPagamentoRequest request) {
        StatusPagamentoResponse response = statusPagamentoService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        statusPagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<StatusPagamentoResponse> buscarPorId(@PathVariable Long id) {
        StatusPagamentoResponse response = statusPagamentoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<StatusPagamentoResponse>> buscarTodos() {
        List<StatusPagamentoResponse> responseList = statusPagamentoService.buscarTodos();
        return ResponseEntity.ok(responseList);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<StatusPagamentoResponse>> buscarPorNome(
            @RequestParam String nome) {
        List<StatusPagamentoResponse> responseList = statusPagamentoService.buscarPorNome(nome);
        return ResponseEntity.ok(responseList);
    }
}