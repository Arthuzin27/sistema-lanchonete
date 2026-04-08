package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.request.StatusEntregaRequest;
import com.arthur.lojaonline.dto.response.StatusEntregaResponse;

import java.util.List;

public interface StatusEntregaService {

    StatusEntregaResponse criar(StatusEntregaRequest request);

    StatusEntregaResponse atualizar(Long id, StatusEntregaRequest request);

    void deletar(Long id);

    StatusEntregaResponse buscarPorId(Long id);

    List<StatusEntregaResponse> buscarPorNome(String nome);

    StatusEntregaResponse buscarPorNomeExato(String nome);

    List<StatusEntregaResponse> buscarTodos();

    StatusEntregaResponse alternarStatusAtivo(Long id);

    void inicializarStatusPadrao();
}