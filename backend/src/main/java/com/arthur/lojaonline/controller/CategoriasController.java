package com.arthur.lojaonline.controller;

import com.arthur.lojaonline.dto.request.CategoriasRequest;
import com.arthur.lojaonline.dto.response.CategoriasResponse;
import com.arthur.lojaonline.service.CategoriasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    private final CategoriasService categoriaService;

    public CategoriasController(CategoriasService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriasResponse> criarCategoria(@Valid @RequestBody CategoriasRequest request) {
        CategoriasResponse response = categoriaService.criarCategoria(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoriasResponse>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasResponse> buscarPorId(@PathVariable Long id) {
        CategoriasResponse response = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriasResponse> atualizarCategoria(
            @PathVariable Long id,
            @Valid @RequestBody CategoriasRequest request) {
        CategoriasResponse response = categoriaService.atualizarCategoria(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}