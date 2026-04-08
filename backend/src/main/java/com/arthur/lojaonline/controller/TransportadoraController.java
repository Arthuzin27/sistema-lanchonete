package com.arthur.lojaonline.controller;

import com.arthur.lojaonline.dto.request.TransportadoraRequest;
import com.arthur.lojaonline.dto.response.TransportadoraResponse;
import com.arthur.lojaonline.service.TransportadoraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transportadoras")
@RequiredArgsConstructor
public class TransportadoraController {

    private final TransportadoraService transportadoraService;

    @PostMapping
    public ResponseEntity<TransportadoraResponse> cadastrar(@RequestBody TransportadoraRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transportadoraService.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<TransportadoraResponse>> listarTodas() {
        return ResponseEntity.ok(transportadoraService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportadoraResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(transportadoraService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransportadoraResponse> atualizar(@PathVariable Long id,
                                                            @RequestBody TransportadoraRequest request) {
        return ResponseEntity.ok(transportadoraService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        transportadoraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}