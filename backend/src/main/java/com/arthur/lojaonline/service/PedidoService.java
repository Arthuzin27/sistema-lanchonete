package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.request.PedidoRequest;
import com.arthur.lojaonline.dto.response.PedidoResponse;

import java.util.List;

public interface PedidoService {

    PedidoResponse cadastrar(PedidoRequest request);

    List<PedidoResponse> listarTodos();

    PedidoResponse buscarPorId(Long id);

    PedidoResponse atualizar(Long id, PedidoRequest request);

    void deletar(Long id);
}