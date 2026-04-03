package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.request.ProdutoRequest;
import com.arthur.lojaonline.dto.response.ProdutoResponse;

import java.util.List;

public interface ProdutoService {

    ProdutoResponse criarProduto(ProdutoRequest request);

    ProdutoResponse buscarPorId(Long id);

    List<ProdutoResponse> listarTodos();

    ProdutoResponse atualizarProduto(Long id, ProdutoRequest request);

    void deletarProduto(Long id);
}