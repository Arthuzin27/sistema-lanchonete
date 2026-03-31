package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.request.CategoriasRequest;
import com.arthur.lojaonline.dto.response.CategoriasResponse;

import java.util.List;

public interface CategoriasService {

    CategoriasResponse criarCategoria(CategoriasRequest request);

    List<CategoriasResponse> listarTodas();

    CategoriasResponse buscarPorId(Long id);

    CategoriasResponse atualizarCategoria(Long id, CategoriasRequest request);

    void deletarCategoria(Long id);
}