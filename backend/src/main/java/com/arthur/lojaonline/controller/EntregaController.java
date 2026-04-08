package com.arthur.lojaonline.controller;

import com.arthur.lojaonline.dto.request.EntregaRequest;
import com.arthur.lojaonline.dto.response.EntregaResponse;
import com.arthur.lojaonline.service.EntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregas")
@RequiredArgsConstructor
public class EntregaController {

    private final EntregaService entregaService;

    @PostMapping
    public ResponseEntity<EntregaResponse> salvar(@RequestBody EntregaRequest request) {
        EntregaResponse response = entregaService.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EntregaResponse>> listarTodos() {
        return ResponseEntity.ok(entregaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(entregaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntregaResponse> atualizar(@PathVariable Long id,
                                                     @RequestBody EntregaRequest request) {
        return ResponseEntity.ok(entregaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        entregaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}