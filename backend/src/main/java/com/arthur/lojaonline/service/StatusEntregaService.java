package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.request.StatusEntregaRequest;
import com.arthur.lojaonline.dto.response.StatusEntregaResponse;
import com.arthur.lojaonline.model.entity.StatusEntrega;

import java.util.List;

public interface StatusEntregaService {
    
    StatusEntregaResponse criar(StatusEntregaRequest request);
    
    StatusEntregaResponse atualizar(Long id, StatusEntregaRequest request);
    
    void deletar(Long id);
    
    StatusEntregaResponse buscarPorId(Long id);
    
    List<StatusEntregaResponse> buscarTodos();
    
    StatusEntrega buscarEntityPorId(Long id);
    
    List<StatusEntregaResponse> buscarPorNome(String nome);
}