package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.request.TransportadoraRequest;
import com.arthur.lojaonline.dto.response.TransportadoraResponse;

import java.util.List;

public interface TransportadoraService {

    TransportadoraResponse cadastrar(TransportadoraRequest request);

    List<TransportadoraResponse> listarTodas();

    TransportadoraResponse buscarPorId(Long id);

    TransportadoraResponse atualizar(Long id, TransportadoraRequest request);

    void deletar(Long id);
}