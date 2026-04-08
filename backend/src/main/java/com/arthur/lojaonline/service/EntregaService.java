package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.request.EntregaRequest;
import com.arthur.lojaonline.dto.response.EntregaResponse;

import java.util.List;

public interface EntregaService {

    EntregaResponse salvar(EntregaRequest request);

    List<EntregaResponse> listarTodos();

    EntregaResponse buscarPorId(Long id);

    EntregaResponse atualizar(Long id, EntregaRequest request);

    void deletar(Long id);
}