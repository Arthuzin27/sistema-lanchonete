package com.arthur.lojaonline.controller;

import com.arthur.lojaonline.dto.request.ProdutoRequest;
import com.arthur.lojaonline.dto.response.ProdutoResponse;
import com.arthur.lojaonline.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ProdutoResponse criar(@RequestBody @Valid ProdutoRequest request) {
        return produtoService.criarProduto(request);
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @GetMapping
    public List<ProdutoResponse> listarTodos() {
        return produtoService.listarTodos();
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoRequest request) {
        return produtoService.atualizarProduto(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }
}