package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.request.PagamentoRequest;
import com.arthur.lojaonline.dto.response.PagamentoResponse;

import java.util.List;

public interface PagamentoService {

    PagamentoResponse cadastrar(PagamentoRequest request);

    List<PagamentoResponse> listarTodos();

    PagamentoResponse buscarPorId(Long id);

    PagamentoResponse atualizar(Long id, PagamentoRequest request);

    void deletar(Long id);
}