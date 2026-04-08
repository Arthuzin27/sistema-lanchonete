package com.arthur.lojaonline.controller;

import com.arthur.lojaonline.dto.request.ItemPedidoRequest;
import com.arthur.lojaonline.dto.response.ItemPedidoResponse;
import com.arthur.lojaonline.service.ItemPedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-pedido")
@RequiredArgsConstructor
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    @PostMapping
    public ResponseEntity<ItemPedidoResponse> cadastrar(@Valid @RequestBody ItemPedidoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemPedidoService.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<ItemPedidoResponse>> listarTodos() {
        return ResponseEntity.ok(itemPedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(itemPedidoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoResponse> atualizar(@PathVariable Long id,
                                                        @Valid @RequestBody ItemPedidoRequest request) {
        return ResponseEntity.ok(itemPedidoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        itemPedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}