package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.request.CategoriaRequest;
import com.arthur.lojaonline.dto.response.CategoriaResponse;

import java.util.List;

public interface CategoriaService {

    CategoriaResponse criarCategoria(CategoriaRequest request);

    List<CategoriaResponse> listarTodas();

    CategoriaResponse buscarPorId(Long id);

    CategoriaResponse atualizarCategoria(Long id, CategoriaRequest request);

    void deletarCategoria(Long id);
}